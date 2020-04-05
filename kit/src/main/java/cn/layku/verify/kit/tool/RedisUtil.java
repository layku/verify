package cn.layku.verify.kit.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Package cn.layku.verify.kit.tool
 * @ClassName RedisUtil
 * @Description TODO
 * @Author dongdingzhuo
 * @Date 2020/04/05 11:19
 */
public class RedisUtil {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil = this;
    }

    /**
     * 设置redis字符串
     *
     * @param k        key
     * @param v        value
     * @param l        过期时间
     * @param timeUnit 时间类型
     */
    public static void setString(String k, String v, long l, TimeUnit timeUnit) {
        redisUtil.stringRedisTemplate.opsForValue().set(k, v, l, timeUnit);
    }

    /**
     * 设置redis字符串
     *
     * @param k key
     * @param v value
     */
    public static void setString(String k, String v) {
        redisUtil.stringRedisTemplate.opsForValue().set(k, v);
    }

    /**
     * 获取redis字符串
     *
     * @param k key
     * @return 返回键值
     */
    public static String getString(String k) {
        return redisUtil.stringRedisTemplate.opsForValue().get(k);
    }

    /**
     * 获取key的过期时间
     *
     * @param k key
     * @return
     */
    public static Long getExpire(String k) {
        return redisUtil.stringRedisTemplate.getExpire(k);
    }

    /**
     * 根据key获取过期时间并换算成指定单位
     *
     * @param k key
     * @return
     */
    public static Long getExpire(String k, TimeUnit timeUnit) {
        return redisUtil.stringRedisTemplate.getExpire(k, timeUnit);
    }

    /**
     * 根据key删除缓存
     *
     * @param k key
     */
    public static void delete(String k) {
        redisUtil.stringRedisTemplate.delete(k);
    }

    /**
     * 检查key是否存在
     *
     * @param k key
     * @return
     */
    public static boolean hasKey(String k) {
        return redisUtil.stringRedisTemplate.hasKey(k);
    }

    /**
     * 设置过期时间
     *
     * @param k        key
     * @param l        过期时间
     * @param timeUnit 过期时间单位
     * @return
     */
    public static boolean expire(String k, long l, TimeUnit timeUnit) {
        return redisUtil.stringRedisTemplate.expire(k, l, timeUnit);
    }

    /**
     * 设置指定时间过期
     *
     * @param k key
     * @param d date
     * @return
     */
    public static boolean expireAt(String k, Date d) {
        return redisUtil.stringRedisTemplate.expireAt(k, d);
    }

    /**
     * 将字符串添加到集合尾部
     *
     * @param k key
     * @param v value
     */
    public static void listPush(String k, String v) {
        redisUtil.stringRedisTemplate.opsForList().rightPush(k, v);
    }

    /**
     * 添加集合到redis
     *
     * @param k    key
     * @param list list
     */
    public static void listPushAll(String k, List<String> list) {
        redisUtil.stringRedisTemplate.opsForList().rightPushAll(k, list);
    }

    /**
     * 获取集合中单个元素
     *
     * @param k key
     * @param l index
     * @return
     */
    public static String listGet(String k, long l) {
        return redisUtil.stringRedisTemplate.opsForList().index(k, l);
    }

    /**
     * 获取集合的大小
     *
     * @param k key
     * @return
     */
    public static Long listSize(String k) {
        return redisUtil.stringRedisTemplate.opsForList().size(k);
    }

    /**
     * 获取集合所有元素
     *
     * @param k
     * @return
     */
    public static List<String> listAll(String k) {
        return redisUtil.stringRedisTemplate.opsForList().range(k, 0, -1);
    }
}
