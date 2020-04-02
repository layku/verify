package cn.layku.verify.rest.service.auth.fallback;

import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.rest.service.auth.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: UserFallback
 * @Package cn.layku.verify.rest.service.auth.fallback
 * @Description: 用户相关接口
 * @date 2020/4/2 13:53
 */
@Service
public class UserFallback implements UserService {
    /**
     * 用户登录
     *
     * @param map
     * @return
     */
    @Override
    public Object login(Map<String, Object> map) {
        return ApiResult.failResult("登录失败,服务忙...请稍后重试！");
    }
}
