package com.bfx.miaosha.dao;

import com.bfx.miaosha.mapper.OrderMapper;
import com.bfx.miaosha.pojo.domain.MiaoshaOrder;
import com.bfx.miaosha.pojo.domain.OrderInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OrderDao {
    @Resource
    private OrderMapper orderMapper;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderMapper.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    public long insertOrder(OrderInfo orderInfo){
        return orderMapper.insert(orderInfo);
    }

    public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder){
        return orderMapper.insertMiaoshaOrder(miaoshaOrder);
    }
}
