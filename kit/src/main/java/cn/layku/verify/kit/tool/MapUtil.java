package cn.layku.verify.kit.tool;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: MapUtil
 * @Package cn.layku.verify.kit.tool;
 * @Description: Map帮助类
 * @date 2020/4/2 10:42
 */
public class MapUtil {
    /**
     * 获取Map中字段的字符串值
     *
     * @param map
     * @param fieldName
     * @return
     */
    public static String getString(Map<String, Object> map, String fieldName) {
        if (map == null) {
            return null;
        }
        Object o = map.get(fieldName);
        if (o == null) {
            return null;
        }
        String s = o.toString().trim();
        if (s.length() == 0 || "undefined".equalsIgnoreCase(s) || "null".equalsIgnoreCase(s)) {
            return null;
        }
        return s;
    }

    /**
     * 获取Map中字段的整数值
     *
     * @param map
     * @param fieldName
     * @return
     */
    public static Integer getInt(Map<String, Object> map, String fieldName) {
        String s = MapUtil.getString(map, fieldName);
        if (s == null) {
            return null;
        }
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取Map中字段的小数值
     *
     * @param map
     * @param fieldName
     * @return
     */
    public static Double getDouble(Map<String, Object> map, String fieldName) {
        String s = MapUtil.getString(map, fieldName);
        if (s == null) {
            return null;
        }
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }
    }
}
