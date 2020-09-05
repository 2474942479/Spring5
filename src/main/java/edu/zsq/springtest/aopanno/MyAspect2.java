package edu.zsq.springtest.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 张
 */
@Component
@Aspect
@Order(2)
public class MyAspect2 {

    /**
     * @After 最终通知 通知方法会在目标方法返回或抛出异常后调用。
     */
    @After("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
    public void after() {
        System.out.println("最终通知2...");
    }

}
