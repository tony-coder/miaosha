package com.bfx.miaosha.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    private Long id;
    private Long userId;  // 用户id
    private Long goodsId;  // 商品id
    private Long deliveryAddrId;  // 收获地址id
    private String goodsName;  // 冗余过来的商品名称
    private Integer goodsCount;  // 商品数量
    private Double goodsPrice;  // 商品单价
    private Integer orderChannel;  // 1:pc,2:android,3:ios
    private Integer status;  // 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
    private Date createDate;  // 订单创建时间
    private Date payDate;  // 支付时间
}
