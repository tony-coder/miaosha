package com.bfx.miaosha.pojo.domain;

import lombok.Data;

@Data
public class Goods {
    private Long id;  // 商品id
    private String goodsName;  // 商品名称
    private String goodsTitle;  // 商品标题
    private String goodsImg;  // 商品的图片
    private String goodsDetail;  // 商品的详情介绍
    private Double goodsPrice;  // 商品单价
    private Integer goodsStock;  // 商品库存，-1表示没有限制
}
