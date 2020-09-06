package edu.zsq.webfluxdemo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.server.WebHandler;

/**
 * @author 张
 */
@SpringBootApplication
@ComponentScan("edu.zsq")
@MapperScan("edu.zsq.webfluxdemo.mapper")
public class WebFluxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxDemoApplication.class, args);
    }

}
