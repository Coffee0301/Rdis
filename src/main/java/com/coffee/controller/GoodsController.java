package com.coffee.controller;

import com.coffee.pojo.User;
import com.coffee.service.IGoodsService;
import com.coffee.service.IUserService;
import com.coffee.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    IUserService userService;

    @Autowired
    IGoodsService goodsService;
//
    @RequestMapping("/toList")
    public String toList(Model model,User user){
        if (null == user){
            return "login";
        }
        List<GoodsVo> goodsList = goodsService.findGoodsList();

        model.addAttribute("goodsList",goodsList);

        return "goodsList";
    }
}
