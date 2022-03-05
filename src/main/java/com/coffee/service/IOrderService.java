package com.coffee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coffee.pojo.Order;
import com.coffee.pojo.User;
import com.coffee.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffee
 * @since 2022-03-01
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goodsVo);
}
