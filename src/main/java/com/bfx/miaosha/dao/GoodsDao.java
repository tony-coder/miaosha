package com.bfx.miaosha.dao;

import com.bfx.miaosha.mapper.GoodsMapper;
import com.bfx.miaosha.pojo.domain.MiaoshaGoods;
import com.bfx.miaosha.pojo.vo.GoodsVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class GoodsDao {
    @Resource
    private GoodsMapper goodsMapper;

    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    public int reduceStock(MiaoshaGoods goods) {
        return goodsMapper.reduceStock(goods);
    }

}
