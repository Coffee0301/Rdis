package com.coffee.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import java.lang.annotation.*;

//注解只是用来做标识,没有实际作用,了解就好
@Documented
//表示这个注解可以作用在什么地方,例如作用在方法上,或作用在某个字段上
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
//被它所注解的注释保留多久
@Retention(RetentionPolicy.RUNTIME)

//表示我们判断逻辑的具体实现类是什么
@Constraint(
        validatedBy = {IsMobileValidator.class}
)

public @interface IsMobile {

    boolean required() default true;
    String message() default "手机号码格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
