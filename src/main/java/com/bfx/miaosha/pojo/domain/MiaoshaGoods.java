package com.bfx.miaosha.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MiaoshaGoods {
    private Long id;
    private Long goodsId;  // 商品id
    private Integer stockCount;  // 库存数量
    private Date startDate;  // 秒杀开始时间
    private Date endDate;  // 秒杀结束时间
}
