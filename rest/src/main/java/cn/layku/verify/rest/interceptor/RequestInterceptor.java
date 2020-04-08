package cn.layku.verify.rest.interceptor;

import cn.layku.verify.kit.tool.DateUtil;
import cn.layku.verify.kit.tool.IpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author dongdingzhuo
 * @Title: RequestInterceptor
 * @Package cn.layku.verify.rest.interceptor
 * @Description: 请求拦截器
 * @date 2020/4/7 16:19
 */
public class RequestInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //客户端IP
        String ip = IpUtil.getIpAddress(request);
        //请求路径
        String uri = request.getRequestURI();
        //请求参数
        String params = getParamsStr(request);

        //请求方法
        String method = request.getMethod();
        //请求头
        Map heads = new LinkedHashMap();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            heads.put(headerName, request.getHeader(headerName));
        }

        //请求时间
        String requestTime = DateUtil.currentDateTimeStr2();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("uri", uri);
        map.put("params", params);
        map.put("method", method);
        map.put("requestTime", requestTime);
        map.put("ip", ip);
        map.put("heads", heads);
        logger.info(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private String getParamsStr(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        Map<String, String[]> paramsMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entrySet = paramsMap.entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values != null) {
                for (String value : values) {
                    result.append(String.format("&%s=%s", key, value));
                }
            } else {
                result.append(String.format("&%s=%s", key, ""));
            }
        }
        return result.toString().replaceFirst("&", "");
    }
}
