package com.coffee.controller;

import com.coffee.pojo.User;
import com.coffee.service.IGoodsService;
import com.coffee.service.IUserService;
import com.coffee.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String toList(Model model,@RequestAttribute(name = "user") User user){
        List<GoodsVo> goodsList = goodsService.findGoodsList();
        model.addAttribute("goodsList",goodsList);
        return "goodsList";
    }
//    @RequestMapping("/toDetail")
//    public String toDetail(Model model,@UserParamResolver User user){
//        if (null == user){
//            return "login";
//        }
//        List<GoodsVo> goodsList = goodsService.findGoodsList();
//        model.addAttribute("goodsList",goodsList);
//        return "goodsList";
//    }
}
