package com.caoyuan.xiao4.pervue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*********************************************************
 @author 曹原
 @date 2020/3/1 16:51
 *********************************************************/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    //静态资源配置的方法,虚拟路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源访问的路径,以及路径访问的实际地址
        registry.addResourceHandler("/pic/**").addResourceLocations("file:D:/pic/");
    }

    //开启跨域访问,单此一个方法可以跨域,但是拦截器会导致此方法失效
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }

    ////以下都是为了跨域
    //private CorsConfiguration addcorsConfig() {
    //    CorsConfiguration corsConfiguration = new CorsConfiguration();
    //    List<String> list = new ArrayList<>();
    //    list.add("*");
    //    corsConfiguration.setAllowedOrigins(list);
    ///*
    //    请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
    //*/
    //    corsConfiguration.addAllowedOrigin("*");
    //    corsConfiguration.addAllowedHeader("*");
    //    corsConfiguration.addAllowedMethod("*");
    //    return corsConfiguration;
    //}
    //
    ////使用过滤器达到跨域请求的目的,防止拦截器导致跨域失败
    //@Bean
    //public CorsFilter corsFilter() {
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    source.registerCorsConfiguration("/**", addcorsConfig());
    //    return new CorsFilter(source);
    //}
}