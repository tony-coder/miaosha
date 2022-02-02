package com.bfx.miaosha.service;

import com.bfx.miaosha.pojo.domain.MiaoshaUser;
import com.bfx.miaosha.pojo.domain.OrderInfo;
import com.bfx.miaosha.pojo.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MiaoshaService {
    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    public OrderInfo doMiaosha(MiaoshaUser user, GoodsVo goods) {
        // 减库存，下订单，写入秒杀订单
        goodsService.reduceStock(goods);
        return orderService.createOrder(user, goods);
    }
}
