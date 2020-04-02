package cn.layku.verify.service.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dongdingzhuo
 * @Title: WebConfig
 * @Package cn.layku.verify.service.file.config
 * @Description: TODO
 * @date 2020/4/2 10:30
 */
public class WebConfig  implements WebMvcConfigurer {
    @Value("${file.path}")
    String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fp/**").addResourceLocations("file:///" + filePath + "/");
    }
}
