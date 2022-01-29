package com.bfx.miaosha.pojo.domain;

import lombok.Data;

@Data
public class MiaoshaOrder {
    private Long id;
    private Long userId;  // 用户id
    private Long orderId;  // 订单id
    private Long goodsId;  // 商品id
}
