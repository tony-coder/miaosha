package com.bfx.miaosha.service;

import com.bfx.miaosha.dao.OrderDao;
import com.bfx.miaosha.pojo.domain.MiaoshaOrder;
import com.bfx.miaosha.pojo.domain.MiaoshaUser;
import com.bfx.miaosha.pojo.domain.OrderInfo;
import com.bfx.miaosha.pojo.vo.GoodsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService {
    @Resource
    private OrderDao orderDao;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    /**
     * 创建订单
     *
     * @param user
     * @param goods
     * @return
     */
    // 使用@Transactional这个注解的类或者方法表示该类里面的所有方法或者这个方法的事务由spring处理，来保证事务的原子性，即是方法里面对数据库操作，如果失败则spring负责回滚操作，成功则提交操作。
    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getGoodsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        // 下订单
        long orderId = orderDao.insertOrder(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        // 写入秒杀订单
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
