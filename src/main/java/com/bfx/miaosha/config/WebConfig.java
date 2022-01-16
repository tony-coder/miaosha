package com.bfx.miaosha.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private UserArgumentResolver userArgumentResolver;
    /**
     * 添加解析器以支持自定义控制器方法参数类型
     * controller中获取参数前获取token得到user传到参数中
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }
}
