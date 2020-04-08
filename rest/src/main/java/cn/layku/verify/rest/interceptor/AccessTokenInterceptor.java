package cn.layku.verify.rest.interceptor;

import cn.layku.verify.kit.constant.ApiCodeConstant;
import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.rest.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: AccessTokenInterceptor
 * @Package cn.layku.verify.rest.interceptor
 * @Description: 验证参数是否正确
 * @date 2020/4/7 19:52
 */
public class AccessTokenInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        String token = request.getParameter("token");
        String timestamp = request.getParameter("timestamp");
        if (StringUtils.isBlank(token)) {
            logger.info("token为空");
            ApiResult.responseResult(response, ApiCodeConstant.PARAM_IS_NULL, "token为空");
            return false;
        }
        if (StringUtils.isBlank(timestamp)) {
            logger.info("timestamp为空");
            ApiResult.responseResult(response, ApiCodeConstant.PARAM_IS_NULL, "timestamp为空");
            return false;
        }
        if (paramsMap.isEmpty()) {
            logger.info("参数为空");
            ApiResult.responseResult(response, ApiCodeConstant.PARAM_IS_NULL, "参数为空");
            return false;
        }
        Map<String, String> paramMap = new HashMap<>();
        for (String s : paramsMap.keySet()) {
            if ("token".equals(s)) {
                continue;
            }
            paramMap.put(s, paramsMap.get(s)[0]);
        }
        int i = TokenUtil.checkAccessToken(token, Long.parseLong(timestamp), paramMap);
        if (i == -1) {
            logger.info("请求超时");
            ApiResult.responseResult(response, ApiCodeConstant.RESP_TIME_OUT, "请求超时");
            return false;
        }
        if (i == -2) {
            logger.info("非法请求");
            ApiResult.responseResult(response, ApiCodeConstant.PARAM_CHECK_FAIL, "非法请求");
            return false;
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
