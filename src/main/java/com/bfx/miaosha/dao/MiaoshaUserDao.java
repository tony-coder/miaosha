package com.bfx.miaosha.dao;

import com.bfx.miaosha.pojo.domain.MiaoshaUser;
import com.bfx.miaosha.mapper.MiaoshaUserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class MiaoshaUserDao {
    @Resource
    private MiaoshaUserMapper miaoshaUserMapper;

    public MiaoshaUser getById(long id){
        return miaoshaUserMapper.getById(id);
    }
}
