package com.yangsx95.demo.sqlattack;

import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Cheri
 * @title: SqlInjectCheck
 * @projectName demo
 * @description: TODO
 * @date 2019/6/21 22:55
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SqlInjectCheck {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
