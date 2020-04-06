package cn.layku.verify.gateway.filter;

import com.alibaba.fastjson.JSON;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Package cn.layku.verify.gateway.filter
 * @ClassName TokenFilter
 * @Description TODO
 * @Author dongdingzhuo
 * @Date 2020/04/05 11:18
 */
@Component
public class TokenFilter  implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String AUTHORIZE_TOKEN = "token";
    private static final String[] EXCLUDE_TOKEN_PATHS = new String[]{
            "/service-auth/login"
    };

    private static final String[] EXCLUDE_AUTH_PATHS = new String[]{
            "/service-auth/logout",
            "/service-file/upload"
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        logger.info("request:" + JSON.toJSONString(request));

//        String path = request.getURI().getPath();
//        //排除不需要token的地址
//        if (Arrays.asList(EXCLUDE_TOKEN_PATHS).contains(path)) {
//            return chain.filter(exchange);
//        }
//
//        MultiValueMap<String, String> queryParams = request.getQueryParams();
//        //token校验
//        String token = queryParams.getFirst(AUTHORIZE_TOKEN);
//        ServerHttpResponse response = exchange.getResponse();
//
//        if (StringUtils.isEmpty(token)) {
//            logger.info("token不能为空");
//            byte[] bytes = "{\"code\":\"401\",\"msg\":\"token不能为空\"}".getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//            return response.writeWith(Flux.just(buffer));
//        }
//
//        String userJson = RedisUtil.getString(token);
//
//        if (StringUtils.isBlank(userJson)) {
//            logger.info("token无效,请重新登录");
//            byte[] bytes = "{\"code\":\"401\",\"msg\":\"token无效,请重新登录\"}".getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//            return response.writeWith(Flux.just(buffer));
//        }
//
//        //排除不需要token权限校验的地址
//        if (Arrays.asList(EXCLUDE_AUTH_PATHS).contains(path)) {
//            return chain.filter(exchange);
//        }
//
//        //权限校验
//        Map<String, Object> userMap = JSON.parseObject(userJson, Map.class);
//        List<String> permissionUrls = (List<String>) userMap.get("permissionUrls");
//        if (!permissionUrls.contains(path.replaceAll("/service-\\w+/", "/"))) {
//            logger.info("访问[" + path + "]权限不足");
//            byte[] bytes = ("{\"code\":\"401\",\"msg\":\"访问[ " + path + "]权限不足\"}").getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
//            return response.writeWith(Flux.just(buffer));
//        }
//
//        RedisUtil.expire(token, 1, TimeUnit.HOURS);

        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String s = new String(content, StandardCharsets.UTF_8);
                        //就是response的值，想修改、查看就随意而为了
                        logger.info("response:" + s);
                        byte[] uppedContent = new String(content, StandardCharsets.UTF_8).getBytes();
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
