package cn.layku.verify.service.file.controller;

import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.kit.tool.DateUtil;
import cn.layku.verify.kit.tool.FileUtil;
import cn.layku.verify.kit.tool.MapUtil;
import cn.layku.verify.service.file.service.FileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: FileController
 * @Package cn.layku.verify.service.file.controller
 * @Description: 文件服务
 * @date 2020/4/2 10:33
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${host.uri}")
    String uri;

    @Value("${file.path}")
    String filePath;

    @Resource(name = "fileService")
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param map
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public ApiResult upload(@RequestParam Map<String, Object> map, @RequestParam("file") MultipartFile file) throws IOException {
        String system = MapUtil.getString(map, "system");
        if (system == null) {
            return ApiResult.failResult("系统代码不能为空");
        }
        String type = MapUtil.getString(map, "type");
        if (type == null) {
            return ApiResult.failResult("文件类型不能为空");
        }
        String fileMd5 = DigestUtils.md5Hex(file.getBytes());
        map.put("md5", fileMd5);
        Map<String, Object> fileInfoMap = fileService.getFileInfo(map);
        if (fileInfoMap != null && system.equals(MapUtil.getString(fileInfoMap, "system")) && type.equals(MapUtil.getString(fileInfoMap, "type"))) {
            return ApiResult.failResult("文件已存在,请勿重复上传");
        }
        String extPath = system + "/" + type;
        String fileFullPath = filePath + "/" + extPath;
        String fileName = FileUtil.fileUpload(file, fileFullPath);
        String uriAddr = uri + "/fp/" + extPath + "/" + fileName;
        //real_name,name,system,type,uri,md5,size,content_type,create_time,year,month,day
        map.put("realName", file.getOriginalFilename());
        map.put("name", fileName);
        map.put("uri", uriAddr);
        map.put("size", file.getSize());
        map.put("contentType", file.getContentType());
        map.put("createTime", DateUtil.currentSeconds());
        map.put("year", DateUtil.currentYearStr());
        map.put("month", DateUtil.currentYearMonthStr());
        map.put("day", DateUtil.currentDateStr());
        fileService.addFileInfo(map);
        return ApiResult.successResultForData(uriAddr);
    }
}
