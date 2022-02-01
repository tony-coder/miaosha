package com.bfx.miaosha.mapper;

import com.bfx.miaosha.pojo.domain.MiaoshaGoods;
import com.bfx.miaosha.pojo.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsMapper {
//    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

//    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") Long goodsId);

//    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    public int reduceStock(MiaoshaGoods goods);
}
