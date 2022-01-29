package com.bfx.miaosha.controller;

import com.bfx.miaosha.pojo.vo.LoginVo;
import com.bfx.miaosha.redis.RedisService;
import com.bfx.miaosha.result.Result;
import com.bfx.miaosha.service.MiaoshaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/login")
public class LoginController {
    @Resource
    private MiaoshaUserService userService;

    @Resource
    private RedisService redisService;

    @RequestMapping(path = "/to_login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(path = "/do_login", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        // 参数校验
//        String passInput = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if (StringUtils.isEmpty(passInput)) {
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if (StringUtils.isEmpty(mobile)) {
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return Result.error((CodeMsg.MOBILE_ERROR));
//        }
        // 登录
        userService.login(response,loginVo);
        return Result.success(true);
    }

}
