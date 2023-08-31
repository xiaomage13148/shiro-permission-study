package com.xiaoma.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoma.sys.mapper")
public class ShiroPermissionStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroPermissionStudyApplication.class, args);
    }

}
