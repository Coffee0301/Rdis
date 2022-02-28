package com.coffee.controller;

import com.coffee.pojo.User;
import com.coffee.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    IUserService userService;

    @RequestMapping("/toList")
    public String toList(Model model, HttpServletResponse response,
                         HttpServletRequest request,
                         @CookieValue(value = "userTicket",required = false)
                                     String ticket){
        if(StringUtils.isEmpty(ticket)){
            return "login";
        }
        User user =userService.getUserByCookie(ticket, request, response);
        if (user==null){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}