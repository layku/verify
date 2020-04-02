package cn.layku.verify.rest.service.file.fallback;

import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.rest.service.file.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: FileFallback
 * @Package cn.layku.verify.rest.service.file.fallback
 * @Description: 文件服务类
 * @date 2020/4/2 10:06
 */
@Service("fileService")
public class FileFallback implements FileService {
    /**
     * 文件上传
     *
     * @param map
     * @param file
     * @return
     */
    @Override
    public Object fileUpload(Map<String, Object> map, MultipartFile file) {
        return ApiResult.failResult("文件上传失败,服务忙...请稍后重试！");
    }
}
