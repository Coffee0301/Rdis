package com.coffee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coffee.mapper.OrderMapper;
import com.coffee.pojo.Order;
import com.coffee.pojo.SeckillGoods;
import com.coffee.pojo.SeckillOrder;
import com.coffee.pojo.User;
import com.coffee.service.IOrderService;
import com.coffee.service.ISeckillGoodsService;
import com.coffee.service.ISeckillOrderService;
import com.coffee.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffee
 * @since 2022-03-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    ISeckillGoodsService seckillGoodsService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ISeckillOrderService seckillOrderService;
    @Override
    public Order seckill(User user, GoodsVo goodsVo) {
        //减库存
        SeckillGoods seckillGoods=seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id",goodsVo.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        seckillGoodsService.updateById(seckillGoods);

        //生成订单
        Order order=new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVo.getId());
        order.setDeliveryAddrId(0);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goodsVo.getGoodsPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateTime(new Date());
        orderMapper.insert(order);

        //生成秒杀订单
        SeckillOrder seckillOrder=new SeckillOrder();
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getId());
        seckillGoods.setGoodsId(goodsVo.getId());
        seckillOrderService.save(seckillOrder);
        return order;
    }
}
