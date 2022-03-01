package com.coffee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coffee.mapper.OrderMapper;
import com.coffee.pojo.Order;
import com.coffee.service.IOrderService;
import org.springframework.stereotype.Service;

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

}
