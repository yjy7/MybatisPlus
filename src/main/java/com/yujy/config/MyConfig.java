package com.yujy.config;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author yujy
 * @title:
 * @projectName
 * @description:
 * @date 2020/10/229:04
 */
@Configuration
@MapperScan("com/yujy/mapper")
public class MyConfig {
    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }


    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /**
     * 逻辑删除插件
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     * sql 执行性能分析插件
     * 开发环境使用，线上不推荐
     *
     * dev 开发环境
     * test  测试环境
     * prod  生产环境
     * @return
     */
    @Bean
    @Profile({"dev","test"}) //设置dev，test环境开启
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor =new PerformanceInterceptor();
        //超过此时间 sql语句不执行  ms
        performanceInterceptor.setMaxTime(500);

        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
