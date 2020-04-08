package cn.layku.verify.rest.service.redis.impl;

import cn.layku.verify.rest.service.redis.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author dongdingzhuo
 * @Title: RedisServiceImpl
 * @Package cn.layku.verify.rest.service.redis.impl
 * @Description: redis服务类
 * @date 2020/4/7 17:27
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置字符串
     *
     * @param k
     * @param v
     */
    @Override
    public void setString(String k, String v) {
        stringRedisTemplate.opsForValue().set(k, v);
    }

    /**
     * 设置带过期时间的字符串
     *
     * @param k
     * @param v
     * @param l
     * @param timeUnit
     */
    @Override
    public void setString(String k, String v, long l, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(k, v, l, timeUnit);
    }

    /**
     * 获取字符串
     *
     * @param k
     * @return
     */
    @Override
    public String getString(String k) {
        return stringRedisTemplate.opsForValue().get(k);
    }

    /**
     * 删除键
     *
     * @param k
     */
    @Override
    public void delete(String k) {
        stringRedisTemplate.delete(k);
    }
}
