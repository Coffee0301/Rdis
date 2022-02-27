package com.coffee.controller;


import com.coffee.pojo.User;
import com.coffee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coffee
 * @since 2022-02-23
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService userService;
    @RequestMapping("test")
    public String hello(Model model){
        User userInfo = userService.getById(1);
        model.addAttribute("name",userInfo.getUserName());
        return "hello";
    }
}
