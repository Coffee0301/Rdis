package com.coffee.controller;

import com.coffee.pojo.User;
import com.coffee.service.IGoodsService;
import com.coffee.service.IUserService;
import com.coffee.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.util.Date;
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

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model,@RequestAttribute(name="user") User user,@PathVariable Long goodsId){
        model.addAttribute("user",user);
        GoodsVo goodsVo=goodsService.findGoodsVoById(goodsId);

        Date startTime=goodsVo.getStartTime();
        Date endTime=goodsVo.getEndTime();
        Date nowDate = new Date();
        //秒杀状态
        int seckillStatus=0;
        //秒杀倒计时
        int remainSeconds=0;
        //秒杀还未开始
        if (nowDate.before(startTime)){
            remainSeconds=(int)((startTime.getTime()-nowDate.getTime())/1000);
        }
        //秒杀已经结束
        else if (nowDate.after(endTime)){
            seckillStatus=2;
            remainSeconds=-1;
        }
        //秒杀中
        else{
            seckillStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("goods",goodsVo);

        return "goodsDetail";
    }
}
