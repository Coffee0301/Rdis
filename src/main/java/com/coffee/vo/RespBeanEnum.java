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
    MOBILE_ERROR(500002,"手机号码格式错误"),
    EMPTY_STOCK(500003,"库存不足，秒杀失败！"),
    REUSE_STOCK(500004,"秒杀商品不能重复抢购")
    ;
    private final long code;
    private final String message;
}
