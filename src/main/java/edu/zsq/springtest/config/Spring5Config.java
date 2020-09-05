package edu.zsq.springtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *  作为配置类，代替xml文件实现全注解开发
 * @EnableAspectJAutoProxy 开启AspectJ 并设置动态代理为cglib动态代理
 * @author 张
 */
@Configuration
@ComponentScan("edu.zsq")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Spring5Config {


}
