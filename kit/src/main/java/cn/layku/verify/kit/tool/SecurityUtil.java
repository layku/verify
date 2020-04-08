package cn.layku.verify.kit.tool;


import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

/**
 * @author dongdingzhuo
 * @Title: SecurityUtil
 * @Package cn.layku.verify.kit.tool
 * @Description: TODO
 * @date 2020/4/7 19:21
 */
public class SecurityUtil {
    /**
     * 字符编码
     */
    private final static String ENCODING = "UTF-8";

    /**
     * 字符串转换为MD5字符串
     *
     * @param str
     * @return
     */
    public static String toMd5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    /**
     * base64加密
     *
     * @param str
     * @return
     */
    public static String toBase64(String str) {
        byte[] b;
        String result = null;
        try {
            b = Base64Utils.encode(str.getBytes(ENCODING));
            result = new String(b, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * base64解密
     *
     * @param str
     * @return
     */
    public static String fromBase64(String str) {
        String result = null;
        try {
            byte[] b = Base64Utils.decode(str.getBytes(ENCODING));
            result = new String(b, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DES加密
     *
     * @param datasource 待加密数据
     * @param key        加密密钥
     * @return
     */
    public static byte[] toDes(byte[] datasource, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * DES解密
     *
     * @param src 待解密数据
     * @param key 解密密钥
     * @return
     */
    public static byte[] fromDes(byte[] src, String key) {
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey secureKey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secureKey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
