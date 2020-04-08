package cn.layku.verify.kit.tool;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dongdingzhuo
 * @Title: IpUtil
 * @Package cn.layku.verify.kit.tool
 * @Description: TODO
 * @date 2020/4/7 16:24
 */
public class IpUtil {
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        boolean unknownFlag = "unknown".equalsIgnoreCase(ip);
        if ((ip == null) || (ip.length() == 0) || unknownFlag) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || unknownFlag) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || unknownFlag) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
