package edu.zsq.springtest.service.impl;

import edu.zsq.springtest.service.BeanLifeCycleService;

public class BeanLifeCycleServiceImpl implements BeanLifeCycleService {

    private String status;

    public BeanLifeCycleServiceImpl() {
        System.out.println("1 调用无参构造方法实例化Bean");
    }

    public void setStatus(String status) {
        System.out.println("2 调用set方法设置属性值");
        this.status = status;
    }

    @Override
    public void initMethod() {
        System.out.println("4调用初始化方法");
    }

    @Override
    public void destroyMethod() {
        System.out.println("7 调用销毁方法");

    }
}
