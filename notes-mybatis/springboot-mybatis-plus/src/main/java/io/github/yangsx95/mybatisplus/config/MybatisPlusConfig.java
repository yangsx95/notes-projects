package com.yangsx95.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author 杨顺翔
 * @since 2022/07/10
 */
@Configurable
@MapperScan("com.yangsx95.mybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * 添加 mp 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    /**
     * 配置seq生成器
     * @return seq生成器
     */
    @Bean
    public IKeyGenerator keyGenerator() {
        return new H2KeyGenerator();
    }
    
}
