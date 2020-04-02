package cn.layku.verify.rest.service.auth;

import cn.layku.verify.rest.service.auth.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: UserService
 * @Package cn.layku.verify.rest.service.auth
 * @Description: 用户相关接口
 * @date 2020/4/2 10:02
 */
@FeignClient(name = "${gateway.auth-user.name}", path = "${gateway.auth-user.path}", url = "${gateway.host}", fallback = UserFallback.class)
public interface UserService {
    /**
     * 用户登录
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/user/login")
    Object login(@RequestParam Map<String, Object> map);
}
