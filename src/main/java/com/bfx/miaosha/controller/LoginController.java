package com.bfx.miaosha.controller;

import com.bfx.miaosha.domain.LoginVo;
import com.bfx.miaosha.redis.RedisService;
import com.bfx.miaosha.result.CodeMsg;
import com.bfx.miaosha.result.Result;
import com.bfx.miaosha.service.MiaoshaUserService;
import com.bfx.miaosha.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        // 参数校验
        String passInput = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(passInput)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.error((CodeMsg.MOBILE_ERROR));
        }
        // 登录
        CodeMsg cm = userService.login(loginVo);
        if (cm.getCode() == 0) {
            return Result.success(true);
        }else{
            return Result.error(cm);
        }
    }

}
