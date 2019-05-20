package com.ayundao.base.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: Permission
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/18 13:50
 * @Description: 权限
 * @Version: V1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String name() default "normal";

    String[] authors() default "";

}
