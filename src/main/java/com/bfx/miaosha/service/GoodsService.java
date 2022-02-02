package com.bfx.miaosha.service;

import com.bfx.miaosha.dao.GoodsDao;
import com.bfx.miaosha.pojo.domain.MiaoshaGoods;
import com.bfx.miaosha.pojo.vo.GoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(Long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();  // 只为了取goodsId
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
