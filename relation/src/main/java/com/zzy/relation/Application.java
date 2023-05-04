package com.zzy.relation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//配置springboot的包扫描
@SpringBootApplication
@MapperScan("com.zzy.relation.mapper")
public class Application {

    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
