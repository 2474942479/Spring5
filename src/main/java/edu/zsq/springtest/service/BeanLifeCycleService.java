package edu.zsq.springtest.service;

/**
 * Bean生命周期
 * @author 张
 */
public interface BeanLifeCycleService {

    /**
     * 初始化Bean
     */
    void initMethod();

    /**
     * 销毁调用的方法
     */
    void destroyMethod();
}
