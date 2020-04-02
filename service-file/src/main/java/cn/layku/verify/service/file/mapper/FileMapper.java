package cn.layku.verify.service.file.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: FileMapper
 * @Package cn.layku.verify.service.file.mapper
 * @Description: 文件服务
 * @date 2020/4/2 10:36
 */
@Repository("fileMapper")
public interface FileMapper {
    /**
     * 添加上传文件信息
     *
     * @param map
     * @return
     */
    int insert(Map<String, Object> map);

    /**
     * 更新文件信息
     *
     * @param map
     * @return
     */
    int update(Map<String, Object> map);

    /**
     * 根据MD5获取文件信息
     *
     * @param map
     * @return
     */
    Map<String, Object> get(Map<String, Object> map);
}
