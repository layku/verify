package cn.layku.verify.kit.tool;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author dongdingzhuo
 * @Title: FileUtil
 * @Package cn.layku.verify.kit.tool;
 * @Description: 文件帮助类
 * @date 2020/4/2 10:44
 */
public class FileUtil {
    public static String fileUpload(MultipartFile multipartFile, String filePath) {
        if (multipartFile != null && multipartFile.getSize() > 0) {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String originalFilename = multipartFile.getOriginalFilename();
            String currentDateTimeWithLineStr = DateUtil.currentDateTimeStr();
            String fileName = currentDateTimeWithLineStr + "_" + UUID.randomUUID().toString().replaceAll("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
            String pathName = filePath + "/" + fileName;
            File newFile = new File(pathName);
            InputStream in = null;
            OutputStream out = null;
            try {
                in = multipartFile.getInputStream();
                out = new FileOutputStream(newFile);
                byte[] buf = new byte[1024 * 8];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
