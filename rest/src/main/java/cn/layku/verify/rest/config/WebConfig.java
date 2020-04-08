package cn.layku.verify.rest.config;

import cn.layku.verify.rest.interceptor.AccessTokenInterceptor;
import cn.layku.verify.rest.interceptor.RequestInterceptor;
import cn.layku.verify.rest.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dongdingzhuo
 * @Title: WebConfig
 * @Package cn.layku.verify.rest.config
 * @Description: 项目相关配置
 * @date 2020/4/7 16:28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
        registry.addInterceptor(new AccessTokenInterceptor());
        registry.addInterceptor(tokenInterceptor());
    }
}

