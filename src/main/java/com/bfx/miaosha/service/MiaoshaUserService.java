package com.bfx.miaosha.service;

import com.bfx.miaosha.dao.MiaoshaUserDao;
import com.bfx.miaosha.domain.MiaoshaUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    @Resource
    private MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }


}
