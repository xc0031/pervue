package com.caoyuan.xiao4.pervue.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*********************************************************
 @author 曹原
 @date 2020/3/1 16:51
 *********************************************************/
@Configuration
@MapperScan("com.caoyuan.xiao4.pervue.mapper")//启动类就不用加了
public class MyPlusConfig {
    //mybatis-plus分页插件,放在启动类中
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(true);
        return paginationInterceptor;
    }
}