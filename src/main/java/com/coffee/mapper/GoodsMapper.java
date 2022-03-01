package com.coffee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coffee.pojo.Goods;
import com.coffee.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author coffee
 * @since 2022-03-01
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVo> findGoodsList();

}
