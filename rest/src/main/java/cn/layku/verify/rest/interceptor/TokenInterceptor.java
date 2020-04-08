package cn.layku.verify.rest.interceptor;

import cn.layku.verify.kit.constant.ApiCodeConstant;
import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.rest.annotation.RequireLogin;
import cn.layku.verify.rest.service.redis.RedisService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author dongdingzhuo
 * @Title: TokenInterceptor
 * @Package cn.layku.verify.rest.interceptor
 * @Description: token拦截器
 * @date 2020/4/7 16:38
 */
public class TokenInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod myHandlerMethod = (HandlerMethod) handler;
            Object bean = myHandlerMethod.getBean();
            Method method = myHandlerMethod.getMethod();
            //类上有登录标记
            Annotation classAnnotation = bean.getClass().getAnnotation(RequireLogin.class);
            //方法上有登录标记
            Annotation methodAnnotation = method.getAnnotation(RequireLogin.class);
            if (classAnnotation != null || methodAnnotation != null) {
                String xToken = request.getHeader("x-token");
                if (StringUtils.isEmpty(xToken)) {
                    logger.info("x-token为空");
                    ApiResult.responseResult(response, ApiCodeConstant.PARAM_IS_NULL, "x-token为空");
                    return false;
                }
                String _token = redisService.getString("token");
                boolean isLogin = xToken.equals(_token);
                if (!isLogin) {
                    //未登录
                    logger.info("未授权接口调用");
                    ApiResult.responseResult(response, ApiCodeConstant.USER_AUTH_FAIL, "未授权接口调用");
                    return false;
                }
            }
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
