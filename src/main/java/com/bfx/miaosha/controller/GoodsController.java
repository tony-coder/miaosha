package com.bfx.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.bfx.miaosha.domain.MiaoshaUser;
import com.bfx.miaosha.service.MiaoshaUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    MiaoshaUserService userService;

    @RequestMapping("/to_list")
    public String list(Model model,
//                       HttpServletResponse response,
                       // @CookieValue的作用，用来获取Cookie中的值
//                       @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
//                       @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken,
                       MiaoshaUser user) {
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//            return "login";
//        }
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//        MiaoshaUser user = userService.getByToken(response, token);
        model.addAttribute("user", user);
        return "goods_list";
    }

    /**
     * 商品详情
     * @param response
     * @param model
     * @param cookieToken
     * @param paramToken
     * @return
     */
    @RequestMapping("/to_detail")
    public String detail(HttpServletResponse response, Model model,
                       // @CookieValue的作用，用来获取Cookie中的值
                       @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                       @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken) {

        return null;
    }
}
