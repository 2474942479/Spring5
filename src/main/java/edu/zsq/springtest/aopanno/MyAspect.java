package edu.zsq.springtest.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 增强类
 *
 * @author 张
 * @Aspect 生成代理对象
 */
@Component
@Aspect
@Order(1)
public class MyAspect {

    /**
     * 声明目标类切入点范围
     * 1.方法必须private，没有返回值，没有参数
     * 2.之后使用将其当成方法调用。例如：@After("myPointcut()")
     */
    @Pointcut("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
    public void pointCut(){

    }

    /**
     * @Before 前置通知  通知方法会在目标方法调用之前执行。
     * @Before("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
     */

    @Before("pointCut()")
    public void before() {
        System.out.println("前置通知...");
    }

    /**
     * @AfterReturning 后置通知(返回通知)   通知方法会在目标方法返回后调用。
     *  @AfterReturning("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
     */

   @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("后置通知...");
    }


    /**
     * @After 最终通知 通知方法会在目标方法返回或抛出异常后调用。
     */
    @After("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
    public void after() {
        System.out.println("最终通知...");
    }

    /**
     * @After 环绕通知  通知方法会将目标方法封装（包裹）起来。最终要返回proceedingJoinPoint.proceed(); 否则无返回值
     */
    @Around("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知前...");
        Object res = proceedingJoinPoint.proceed();
        System.out.println("环绕通知后...");
        return res;
    }

    /**
     * @AfterThrowing 异常通知   通知方法会在目标方法抛出异常后调用。
     */
    @AfterThrowing("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
    public void afterThrowing() {
        System.out.println("异常通知...");
    }
}
