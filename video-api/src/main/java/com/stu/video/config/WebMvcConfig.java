package com.stu.video.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 14:47
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public ApiInterceptor apiInterceptor(){
        return new ApiInterceptor();
    }

    /**
     * 拦截器注册 设置拦截接口
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/user/**")
                .addPathPatterns("/video/upload", "/video/uploadCover", "/video/userLike", "/video/userUnLike", "/video/saveComment")
                .addPathPatterns("/bgm/**")
                .excludePathPatterns("/user/queryPublisher");

        super.addInterceptors(registry);
    }

}
