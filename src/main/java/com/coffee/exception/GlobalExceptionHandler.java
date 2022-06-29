package com.coffee.exception;

import com.coffee.vo.RespBean;
import com.coffee.vo.RespBeanEnum;
import org.springframework.context.annotation.Import;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author 12072
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public RespBean exceptionHandler(Exception e){

        if (e instanceof GlobalException){
            GlobalException e1=(GlobalException) e;
            return RespBean.error(e1.getRespBeanEnum());
        }else if (e instanceof BindException){
            BindException e1=(BindException) e;
            RespBean respBean= RespBean.error(RespBeanEnum.LOGIN_ERROR);
            respBean.setCode(500005);
            respBean.setMessage("参数校验异常："+e1.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
