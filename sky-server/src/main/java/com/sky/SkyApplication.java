package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 开启注解方式的事务管理
@Slf4j
@EnableDubbo
public class SkyApplication {

    /**
     * Main entry point for the Sky application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");
    }
}
