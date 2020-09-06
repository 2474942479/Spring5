package edu.zsq.springtest.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.security.PublicKey;

/**
 *  作为配置类，代替xml文件实现全注解开发
 * @EnableAspectJAutoProxy 开启AspectJ 并设置动态代理为cglib动态代理
 * @EnableTransactionManagement 开启事务
 * @author 张
 */
@Configuration
@ComponentScan("edu.zsq")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
public class Spring5Config {

    /**
     * 创建数据库连接池
     * @return
     */
    @Bean
    public DruidDataSource getDruidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3307/mybatis_plus");
        druidDataSource.setName("root");
        druidDataSource.setPassword("root");
        return druidDataSource;
    }


    /**
     *  创建JdbcTemplate对象
     * @return
     */
    @Bean
    public JdbcTemplate getJdbcTemplate(DruidDataSource druidDataSource){

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(druidDataSource);
        return jdbcTemplate;
    }

    /**
     * 创建事务管理器对象
     * @return
     */
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DruidDataSource druidDataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(druidDataSource);
        return transactionManager;
    }

}
