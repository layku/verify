package cn.layku.verify.rest.controller.auth;

import cn.layku.verify.kit.tool.ApiResult;
import cn.layku.verify.kit.tool.MapUtil;
import cn.layku.verify.rest.service.auth.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: UserController
 * @Package cn.layku.verify.rest.controller.auth
 * @Description: 用户相关接口
 * @date 2020/4/2 9:57
 */
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestParam Map<String, Object> map) {
        logger.info("[/rest/login]->params->{}", JSON.toJSONString(map));

        String account = MapUtil.getString(map, "account");
        if (account == null) {
            return ApiResult.failResult("账号不能为空");
        }
        String password = MapUtil.getString(map, "password");
        if (password == null) {
            return ApiResult.failResult("密码不能为空");
        }
        return userService.login(map);
    }

}
