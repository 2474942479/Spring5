package edu.zsq.springtest;

import edu.zsq.springtest.config.Spring5Config;
import edu.zsq.springtest.dao.UserDao;
import edu.zsq.springtest.entity.CollectionTypeTest;
import edu.zsq.springtest.entity.User;
import edu.zsq.springtest.service.UserService;
import edu.zsq.springtest.service.impl.BeanLifeCycleServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class spring5test {


    @Test
    /**
     * 测试ioc实现原理
     */
    public void test() {

//        IOC 实现原理: 工厂模式  通过xml配置文件获取类的全路径  再根据反射的Class.forName("全路径")获取类的class文件,
//        再通过class，newInstance()实例化对象  从而实现解耦
//        BeanFactory：IOC容器基本实现,


//        加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
//        获取配置创建的对象，并转换类型
        User user = context.getBean("user", User.class);
        String id = user.getId();
        System.out.println(id+":"+user.getName());

    }


    /**
     * 练习1 向类中注入对象
     * 练习2 通过名称自动注入对象
     */
    @Test
    public void test2(){

        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.add());

    }

    /**
     * 测试集合类型的属性值注入 并抽取公共部分
     */

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("collectionTestBean.xml");
        CollectionTypeTest collectionTest = context.getBean("collectionTest", CollectionTypeTest.class);
        System.out.println(collectionTest);
    }

    /**
     * 工程bean 返回类性可以和bean中定义的不一样
     */
    @Test
    public void test4(){
        ApplicationContext context = new ClassPathXmlApplicationContext("FactoryBeanTest.xml");
        User user = context.getBean("factoryBeanTest", User.class);
        System.out.println(user);
    }

    /**
     * 通过设置bean的scope值
     *  singleton和prototype区别。
     * 第一singleton单实例，prototype多实例。
     * 第二设置scope值是singleton时候，加载spring配置文件时候就会创建单实例对象
     * 设置 scope值是prototype时候，不是在加载spring 配置文件时候创建对象，在调用 getBean方法时候创建多实例对象
     */
    @Test
    public void test5(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
//        获取配置创建的对象，并转换类型
        User user1 = context.getBean("user", User.class);
        User user2 = context.getBean("user", User.class);
        System.out.println(user1+ "\n" + user2);
    }

    /**
     * Bean的生命周期  7步
     */
    @Test
    public void test6(){
//        ClassPathXmlApplicationContext有close()方法  手动销毁
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanLifeCycle.xml");
//        获取配置创建的对象，并转换类型
        BeanLifeCycleServiceImpl lifeCycle = context.getBean("lifeCycle", BeanLifeCycleServiceImpl.class);
        System.out.println("6 获取Bean对象，并进行操作"+"\n"+lifeCycle);
        context.close();
    }

    /**
     * 练习 通过注解实现对象创建
     * 并通过@Autowried @Qualifier  @Resources实现注入对象
     * 通过@Value 注入属性值
     */
    @Test
    public void test7(){

        ApplicationContext context = new ClassPathXmlApplicationContext("annotationBeanTest.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        System.out.println(userService.add());

    }

    /**
     * 纯注解开发
     */
    @Test
    public void test8(){
//        加载注解配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring5Config.class);
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        System.out.println(userService.add());
    }
}
