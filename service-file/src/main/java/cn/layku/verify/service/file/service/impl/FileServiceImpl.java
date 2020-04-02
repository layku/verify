package cn.layku.verify.service.file.service.impl;

import cn.layku.verify.service.file.mapper.FileMapper;
import cn.layku.verify.service.file.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: FileService
 * @Package cn.layku.verify.service.file.service.impl
 * @Description: 文件服务类
 * @date 2020/4/2 10:34
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource(name = "fileMapper")
    private FileMapper fileMapper;

    /**
     * 添加文件信息
     *
     * @param map
     * @return
     */
    @Override
    public int addFileInfo(Map<String, Object> map) {
        return fileMapper.insert(map);
    }

    /**
     * 更新文件信息
     *
     * @param map
     * @return
     */
    @Override
    public int updateFileInfo(Map<String, Object> map) {
        return fileMapper.update(map);
    }

    /**
     * 根据mad5获取文件信息
     *
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> getFileInfo(Map<String, Object> map) {
        return fileMapper.get(map);
    }
}
