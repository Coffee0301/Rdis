package com.coffee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coffee.pojo.Goods;
import com.coffee.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffee
 * @since 2022-03-01
 */
public interface IGoodsService extends IService<Goods> {
    List<GoodsVo> findGoodsList();
}
