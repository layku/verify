package cn.layku.verify.rest.service.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author dongdingzhuo
 * @Title: RedisService
 * @Package cn.layku.verify.rest.service.redis
 * @Description: redis服务类
 * @date 2020/4/7 17:24
 */
public interface RedisService {
    /**
     * 设置字符串
     *
     * @param k
     * @param v
     */
    void setString(String k, String v);

    /**
     * 设置带过期时间的字符串
     *
     * @param k
     * @param v
     * @param l
     * @param timeUnit
     */
    void setString(String k, String v, long l, TimeUnit timeUnit);

    /**
     * 获取字符串
     *
     * @param k
     * @return
     */
    String getString(String k);

    /**
     * 删除键
     *
     * @param k
     */
    void delete(String k);


}
