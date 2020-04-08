package cn.layku.verify.rest.interceptor;

import cn.layku.verify.kit.constant.ApiCodeConstant;
import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.kit.tool.IpUtil;
import cn.layku.verify.rest.service.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author dongdingzhuo
 * @Title: IpBlackListInterceptor
 * @Package cn.layku.verify.rest.interceptor
 * @Description: 黑名单
 * @date 2020/4/8 18:03
 */
public class IpBlackListInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //客户端IP
        String ip = IpUtil.getIpAddress(request);
        //请求路径
        String uri = request.getRequestURI();
        String k = ip + ":" + uri;
        String count = redisService.getString(k);
        if (count == null) {
            redisService.setString(k, "1", 10, TimeUnit.SECONDS);
        } else {
            int c = Integer.parseInt(count);
            if (c > 5) {
                logger.info("访问频繁");
                ApiResult.responseResult(response, ApiCodeConstant.ACCESS_OFTER, "访问频繁");
                return false;
            }
            redisService.setString(k, (c + 1) + "", 10, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
