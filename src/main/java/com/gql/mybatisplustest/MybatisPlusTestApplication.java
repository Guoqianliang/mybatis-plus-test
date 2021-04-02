package com.gql.mybatisplustest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: springboot启动类
 * 1).问题1:启动类上为什么要使用MapperScan对mapper接口进行扫描?
 *      因为Usermapper是一个接口,接口动态生成实现类对象默认是找不到的,需要添加MapperScan才能找到动态生成的对象
 * @author Guoqianliang
 * @date 11:24 - 2021/4/1
 */
@SpringBootApplication
@MapperScan("com.gql.mybatisplustest.mapper")
public class MybatisPlusTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusTestApplication.class, args);
    }

}
