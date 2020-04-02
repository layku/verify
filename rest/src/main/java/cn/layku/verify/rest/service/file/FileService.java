package cn.layku.verify.rest.service.file;

import cn.layku.verify.rest.service.file.fallback.FileFallback;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: FileService
 * @Package cn.layku.verify.rest.service.file
 * @Description: 文件服务类
 * @date 2020/4/2 10:03
 */
@FeignClient(name = "${gateway.file.name}", path = "${gateway.file.path}", url = "${gateway.host}", fallback = FileFallback.class, configuration = FileService.FileConfig.class)
public interface FileService {

    /**
     * 文件上传
     *
     * @param map
     * @param file
     * @return
     */
    @PostMapping(value = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Object fileUpload(@RequestParam Map<String, Object> map, @RequestPart("file") MultipartFile file);


    class FileConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
