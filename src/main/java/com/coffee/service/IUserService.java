package com.coffee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coffee.pojo.User;
import com.coffee.vo.LoginVo;
import com.coffee.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffee
 * @since 2022-02-23
 */
public interface IUserService extends IService<User> {

    RespBean login(LoginVo loginVo);

//    User getUserByCookie(String ticket,HttpServletRequest request,HttpServletResponse response);

}
