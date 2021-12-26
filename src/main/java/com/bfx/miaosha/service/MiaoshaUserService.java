package com.bfx.miaosha.service;

import com.bfx.miaosha.dao.MiaoshaUserDao;
import com.bfx.miaosha.domain.LoginVo;
import com.bfx.miaosha.domain.MiaoshaUser;
import com.bfx.miaosha.result.CodeMsg;
import com.bfx.miaosha.util.MD5Util;
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

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(formPass, saltDB);  // 二次MD5
        if (!calcPass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }

}
