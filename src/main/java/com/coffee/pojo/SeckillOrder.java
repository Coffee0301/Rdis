package com.coffee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author coffee
 * @since 2022-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_seckill_order")
public class SeckillOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 订单Id
     */
    private Long orderId;

    /**
     * 商品Id
     */
    private Long goodsId;


}
