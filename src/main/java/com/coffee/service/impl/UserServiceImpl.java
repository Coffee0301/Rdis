package com.coffee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coffee.exception.GlobalException;
import com.coffee.mapper.UserMapper;
import com.coffee.pojo.User;
import com.coffee.service.IUserService;
import com.coffee.utils.CookieUtil;
import com.coffee.utils.MD5Util;
import com.coffee.utils.UUIDUtil;
import com.coffee.vo.LoginVo;
import com.coffee.vo.RespBean;
import com.coffee.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffee
 * @since 2022-02-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public RespBean login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        User user = userMapper.selectById(mobile);
        if (user==null){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        String pass = MD5Util.fromPassToDBPass(password, user.getSalt());
        if (!pass.equals(user.getPassWord())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        //通过uuid获取随机ticket
        String ticket = UUIDUtil.uuid();

        redisTemplate.opsForValue().set("user:"+ticket,user);

//        通过HttpServletRequest获取session并设置setAttribute(ticket,user);
//        request.getSession().setAttribute(ticket,user);

//        //通过CookieUtil获取cookie并设置userTicket
//        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }

//    public User getUserByCookie(String ticket,HttpServletRequest request,HttpServletResponse response){
//        if (StringUtils.isEmpty(ticket)){
//            return null;
//        }
//        User user =(User) redisTemplate.opsForValue().get("user:" + ticket);
//        if (user!=null){
//           CookieUtil.setCookie(request,response,"userTicket",ticket);
//        }
//        return user;
//    }

}
