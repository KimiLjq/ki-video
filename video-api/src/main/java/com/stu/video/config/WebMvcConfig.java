package com.stu.video.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/22 14:47
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

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
//        registry.addInterceptor(apiInterceptor()).addPathPatterns("/user/**")
//                .addPathPatterns("/video/upload", "/video/uploadCover", "/video/userLike", "/video/userUnLike", "/video/saveComment")
//                .addPathPatterns("/bgm/**")
//                .excludePathPatterns("/user/queryPublisher");
//
//        super.addInterceptors(registry);
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/ki-video/user/autoLogin");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //项目中的所有接口都支持跨域
        registry.addMapping("/**")
                //所有地址都可以访问，也可以配置具体地址
                .allowedOriginPatterns("*")
                //允许的请求方式
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //是否支持跨域Cookie
                .allowCredentials(true)
                // 跨域允许时间
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/images/module/**", "/static/images/bg/**", "/ki-video/user/avatar/**").addResourceLocations("file:D:/IDEA/workspace/ki-video/static/images/module/", "file:D:/IDEA/workspace/ki-video/static/images/bg/", "file:D:/IDEA/workspace/ki-video/static/avatar/");

    }
}
