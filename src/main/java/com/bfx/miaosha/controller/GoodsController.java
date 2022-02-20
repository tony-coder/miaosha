package com.bfx.miaosha.controller;

import com.bfx.miaosha.pojo.domain.MiaoshaUser;
import com.bfx.miaosha.pojo.vo.GoodsVo;
import com.bfx.miaosha.service.GoodsService;
import com.bfx.miaosha.service.MiaoshaUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    //    @Resource
//    MiaoshaUserService userService;
    @Resource
    private GoodsService goodsService;

    /**
     * QPS: 1295
     * 数据量: 5000*10
     * @param model
     * @param user
     * @return
     */
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
        // 查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
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
