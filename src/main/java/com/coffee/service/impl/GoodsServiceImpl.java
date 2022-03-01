package com.coffee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coffee.mapper.GoodsMapper;
import com.coffee.pojo.Goods;
import com.coffee.service.IGoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
