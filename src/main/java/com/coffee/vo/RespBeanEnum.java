package com.coffee.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum RespBeanEnum {

    SUCCESS(200,"success"),
    ERROR(500,"服务器内部错误"),
    LOGIN_ERROR(500001,"用户名或密码错误,请重新输入"),
    MOBILE_ERROR(500001,"手机号码格式错误")
    ;
    private final long code;
    private final String message;
}
