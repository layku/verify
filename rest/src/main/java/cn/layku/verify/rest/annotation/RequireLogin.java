package cn.layku.verify.rest.annotation;

import java.lang.annotation.*;

/**
 * @author dongdingzhuo
 * @Title: RequireLogin
 * @Package cn.layku.verify.rest.annotation
 * @Description: TODO
 * @date 2020/4/7 16:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface RequireLogin {
}
