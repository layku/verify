package cn.layku.verify.rest.controller.file;

import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.kit.tool.MapUtil;
import cn.layku.verify.rest.service.file.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: FileController
 * @Package cn.layku.verify.rest.controller.file
 * @Description: 文件相关接口
 * @date 2020/4/2 9:58
 */
@RestController
public class FileController {

    @Resource(name = "fileService")
    private FileService service;

    /**
     * 上传文件
     *
     * @param map
     * @param file
     * @return
     */
    @PostMapping(value = "/fileUpload")
    public Object fileUpload(@RequestParam Map<String, Object> map, @RequestParam("file") MultipartFile file) {
        String token = MapUtil.getString(map, "token");
        if (token == null) {
            return ApiResult.failResult("token不能为空");
        }
        String system = MapUtil.getString(map, "system");
        if (system == null) {
            return ApiResult.failResult("系统代码不能为空");
        }
        String type = MapUtil.getString(map, "type");
        if (type == null) {
            return ApiResult.failResult("文件类型不能为空");
        }
        return service.fileUpload(map, file);
    }


}
