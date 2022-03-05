package com.coffee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coffee.pojo.Order;
import com.coffee.pojo.SeckillOrder;
import com.coffee.pojo.User;
import com.coffee.service.IGoodsService;
import com.coffee.service.IOrderService;
import com.coffee.service.ISeckillGoodsService;
import com.coffee.service.ISeckillOrderService;
import com.coffee.vo.GoodsVo;
import com.coffee.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    IGoodsService goodsService;

    @Autowired
    ISeckillOrderService seckillOrderService;

    @Autowired
    IOrderService orderService;
    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, Long goodsId, @RequestAttribute("user") User user){
        //判断库存
        GoodsVo goodsVo=goodsService.findGoodsVoById(goodsId);
        if (goodsVo.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK);
            return "seckillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder=seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
                .eq("user_id",user.getId())
                .eq("goods_id",goodsVo.getId()));
        if (seckillOrder!=null){
            model.addAttribute("errmsg",RespBeanEnum.REUSE_STOCK);
            return "seckillFail";
        }
        Order order=orderService.seckill(user,goodsVo);

        model.addAttribute("order",order);
        return "orderDetail";
    }
}
