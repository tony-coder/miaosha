package com.bfx.miaosha.service;

import com.alibaba.druid.util.StringUtils;
import com.bfx.miaosha.dao.MiaoshaUserDao;
import com.bfx.miaosha.pojo.vo.LoginVo;
import com.bfx.miaosha.pojo.domain.MiaoshaUser;
import com.bfx.miaosha.exception.GlobalException;
import com.bfx.miaosha.redis.MiaoshaUserKey;
import com.bfx.miaosha.redis.RedisService;
import com.bfx.miaosha.result.CodeMsg;
import com.bfx.miaosha.util.MD5Util;
import com.bfx.miaosha.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    @Resource
    private MiaoshaUserDao miaoshaUserDao;

    @Resource
    private RedisService redisService;

    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    public void login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(formPass, saltDB);  // 二次MD5
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        // 生成cookie
        String token = UUIDUtil.uuid();
        // 标识token对应哪个用户，把token写道redis中
        addCookie(response, token, user);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        // 延长有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        // 前缀+token:user
        redisService.set(MiaoshaUserKey.token, token, user);
        log.info("存入redis: {}", token);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
