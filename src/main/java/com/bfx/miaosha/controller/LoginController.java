package com.bfx.miaosha.controller;

import com.bfx.miaosha.domain.LoginVo;
import com.bfx.miaosha.redis.RedisService;
import com.bfx.miaosha.result.Result;
import com.bfx.miaosha.service.MiaoshaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Slf4j
public class LoginController {
    @Resource
    private MiaoshaUserService userService;

    @Resource
    private RedisService redisService;

    @RequestMapping(path = "/login/to_login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(path = "/login/do_login", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> doLogin(LoginVo loginVo) {
        log.info(loginVo.toString());
        return Result.success(true);
    }

}
