package cn.layku.verify.service.file.service;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: FileService
 * @Package cn.layku.verify.service.file.service
 * @Description: 文件服务
 * @date 2020/4/2 10:34
 */
public interface FileService {

    /**
     * 添加文件信息
     *
     * @param map
     * @return
     */
    int addFileInfo(Map<String, Object> map);

    /**
     * 更新文件信息
     *
     * @param map
     * @return
     */
    int updateFileInfo(Map<String, Object> map);

    /**
     * 根据mad5获取文件信息
     *
     * @param map
     * @return
     */
    Map<String, Object> getFileInfo(Map<String, Object> map);
}
