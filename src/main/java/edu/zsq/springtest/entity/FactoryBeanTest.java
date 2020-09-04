package edu.zsq.springtest.entity;

import org.hamcrest.Factory;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean：配置文件定义的Bean可以和返回类型不一样
 */
public class FactoryBeanTest implements FactoryBean<User> {

    /**
     * 定义返回的bean
     * @return
     * @throws Exception
     */
    @Override
    public User getObject() throws Exception {
        Class<?> clazz = Class.forName("edu.zsq.springtest.entity.User");
        User user =(User) clazz.newInstance();
//        User user = new User();
        user.setId("4");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
