package com.task2group13.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 前后端项目分离解决cors错误

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        //设置允许跨域的路径
        registry.addMapping ("/**")
        //设置允许跨域请求的域名
        .allowedOriginPatterns ("*")
        //是否允许证书
        .allowCredentials (true)
        //设置允许的方法
        .allowedMethods ("GET","POST")
        //设置允许的header属性
        .allowedHeaders ("*")
        //允许跨域时间
        .maxAge (3600);
    }

}