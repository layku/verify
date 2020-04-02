package cn.layku.verify.service.auth.controller;

import cn.layku.verify.kit.tool.ApiResult;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: UserController
 * @Package cn.layku.verify.service.auth.controller
 * @Description: 用户相关
 * @date 2020/4/2 11:28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/login")
    public ApiResult login(@RequestParam Map<String, Object> map) {
        logger.info("[/service-auth/user/login]->params->{}", JSON.toJSONString(map));
        return ApiResult.successResult("login success");
    }

}
