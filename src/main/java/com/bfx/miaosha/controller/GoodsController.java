package com.bfx.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.bfx.miaosha.pojo.domain.MiaoshaUser;
import com.bfx.miaosha.pojo.vo.GoodsVo;
import com.bfx.miaosha.redis.GoodsKey;
import com.bfx.miaosha.redis.RedisService;
import com.bfx.miaosha.service.GoodsService;
import com.bfx.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.context.webflux.SpringWebFluxContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    //    @Resource
//    MiaoshaUserService userService;
    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    /**
     * QPS: 1295
     * 数据量: 5000*10
     * <p>
     * 优化：页面缓存
     * QPS: 1334
     * error: 92.36%
     *
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response,
                       Model model,
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
        // 取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        // 查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);

        // thymeleaf.spring5的API中把大部分的功能移到了IWebContext下面,用来区分边界。剔除了ApplicationContext 过多的依赖，现在thymeleaf渲染不再过多依赖spring容器
        IWebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());

        // 手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if (!StringUtils.isEmpty(html)) {
            // 存页面缓存
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;
    }

    /**
     * 商品详情
     * <p>
     * 优化：url级缓存
     *
     * @param model
     * @param user
     * @return
     */
    @GetMapping(value = "/to_detail2/{goodsId}", produces = "text/html")
    @ResponseBody
    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model,
                          // HttpServletResponse response,
                          // @CookieValue的作用，用来获取Cookie中的值
//                         @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
//                         @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken,
                          MiaoshaUser user, @PathVariable("goodsId") Long goodsId) {
        model.addAttribute("user", user);

        String html = redisService.get(GoodsKey.getGoodsDetail, String.valueOf(goodsId), String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long currentTime = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (currentTime < startTime) {  // 秒杀还未开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) (startTime - currentTime) / 1000;
        } else if (currentTime > endTime) {  // 秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        IWebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if (!StringUtils.isEmpty(html)) {
            // 存页面缓存
            redisService.set(GoodsKey.getGoodsList, String.valueOf(goodsId), html);
        }
        return html;
    }

    /**
     * 商品详情
     *
     * @param model
     * @param user
     * @return
     */
    @GetMapping("/to_detail/{goodsId}")
    public String detail(Model model,
                         // HttpServletResponse response,
                         // @CookieValue的作用，用来获取Cookie中的值
//                         @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
//                         @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken,
                         MiaoshaUser user, @PathVariable("goodsId") Long goodsId) {
        model.addAttribute("user", user);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long currentTime = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (currentTime < startTime) {  // 秒杀还未开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) (startTime - currentTime) / 1000;
        } else if (currentTime > endTime) {  // 秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
