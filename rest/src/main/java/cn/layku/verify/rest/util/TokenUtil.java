package cn.layku.verify.rest.util;

import cn.layku.verify.kit.constant.ApiCodeConstant;
import cn.layku.verify.kit.constant.OtherConstant;
import cn.layku.verify.kit.tool.DateUtil;
import cn.layku.verify.kit.tool.SecurityUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author dongdingzhuo
 * @Title: LoginUtil
 * @Package cn.layku.verify.rest.util
 * @Description: token帮助类
 * @date 2020/4/7 16:50
 */
public class TokenUtil {

    public static String generateToken() {
        StringBuilder sb = new StringBuilder();
        sb.append(UUID.randomUUID());
        return sb.toString();
    }

    public static int checkAccessToken(String token, Long timestamp, Map<String, String> params) {
        long currentSeconds = DateUtil.currentSeconds();
        if (timestamp + OtherConstant.NETWORK_TIME_OUT_MAX < currentSeconds) {
            return -1;
        }
        Map<String, String> map = new TreeMap<>();
        map.putAll(params);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> s : map.entrySet()) {
            String key = s.getKey();
            String value = s.getValue();
            if (StringUtils.isBlank(value)) {//过滤空值
                continue;
            }
            //拼接
            sb.append(key).append("=").append(value).append("&");
        }
        if (!map.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        String _token = SecurityUtil.toMd5(sb.toString());
        if (!_token.equals(token)) {
            return -2;
        }
        return 0;
    }

}
