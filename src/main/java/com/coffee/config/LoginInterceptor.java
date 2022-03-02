package com.coffee.config;

import com.coffee.pojo.User;
import com.coffee.utils.CookieUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     *在请求处理之前进行调用(Controller方法调用之前)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行了TestInterceptor的preHandle方法");
        try {
            //统一拦截(查询当前session是否存在user)(user会在每次登录成功后,写入session)
            String ticket = CookieUtil.getCookieValue(request, "userTicket");
            User user = (User)redisTemplate.opsForValue().get("user:" + ticket);
            if (user!=null){
                request.setAttribute("user",user);
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/login/toLogin");
        }catch (IOException e){
            e.printStackTrace();
        }

        return false;//如果设置为false时，被请求时拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }
}
