package edu.zsq.springtest;

import edu.zsq.springtest.config.Spring5Config;
import edu.zsq.springtest.dao.UserDao;
import edu.zsq.springtest.dao.impl.UserDaoImpl;
import edu.zsq.springtest.entity.CollectionTypeTest;
import edu.zsq.springtest.entity.User;
import edu.zsq.springtest.service.UserService;
import edu.zsq.springtest.service.impl.BeanLifeCycleServiceImpl;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        System.out.println(id + ":" + user.getName());

    }


    /**
     * 练习1 向类中注入对象
     * 练习2 通过名称自动注入对象
     */
    @Test
    public void test2() {

        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.add());

    }

    /**
     * 测试集合类型的属性值注入 并抽取公共部分
     */

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("collectionTestBean.xml");
        CollectionTypeTest collectionTest = context.getBean("collectionTest", CollectionTypeTest.class);
        System.out.println(collectionTest);
    }

    /**
     * 工程bean 返回类性可以和bean中定义的不一样
     */
    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("FactoryBeanTest.xml");
        User user = context.getBean("factoryBeanTest", User.class);
        System.out.println(user);
    }

    /**
     * 通过设置bean的scope值
     * singleton和prototype区别。
     * 第一singleton单实例，prototype多实例。
     * 第二设置scope值是singleton时候，加载spring配置文件时候就会创建单实例对象
     * 设置 scope值是prototype时候，不是在加载spring 配置文件时候创建对象，在调用 getBean方法时候创建多实例对象
     */
    @Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
//        获取配置创建的对象，并转换类型
        User user1 = context.getBean("user", User.class);
        User user2 = context.getBean("user", User.class);
        System.out.println(user1 + "\n" + user2);
    }

    /**
     * Bean的生命周期  7步
     */
    @Test
    public void test6() {
//        ClassPathXmlApplicationContext有close()方法  手动销毁
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanLifeCycle.xml");
//        获取配置创建的对象，并转换类型
        BeanLifeCycleServiceImpl lifeCycle = context.getBean("lifeCycle", BeanLifeCycleServiceImpl.class);
        System.out.println("6 获取Bean对象，并进行操作" + "\n" + lifeCycle);
        context.close();
    }

    /**
     * 练习 通过注解实现对象创建
     * 并通过@Autowried @Qualifier  @Resources实现注入对象
     * 通过@Value 注入属性值
     */
    @Test
    public void test7() {

        ApplicationContext context = new ClassPathXmlApplicationContext("annotationBeanTest.xml");
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        System.out.println(userService.add());

    }

    /**
     * 纯注解开发
     */
    @Test
    public void test8() {
//        加载注解配置类
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring5Config.class);
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        System.out.println(userService.add());
    }

    /**
     * 练习 AOP 原理： 有接口 使用JDK动态代理
     */
    @Test
    public void jDKProxy() {

        ApplicationContext context = new AnnotationConfigApplicationContext(Spring5Config.class);
        UserDao userDao = context.getBean("userDaoImpl1", UserDao.class);

//        动态代理  方法一：通过lambda表达式实现动态代理
        UserDao userDaoProxy = (UserDao) Proxy.newProxyInstance(spring5test.class.getClassLoader(), new Class[]{
                UserDao.class}, (proxy, method, args) -> {
            System.out.println(method.getName() + "方法执行前执行了.............");
//            通过判断方法名  实现哪些方法可以被代理
            if (!"inset".equals(method.getName())) {
                return null;
            }
            String res = (String) method.invoke(userDao, args);
            System.out.println(method.getName() + "方法执行后执行了.............");

            return res;
        });
//        方法二： 通过外部类实现
        UserDao userDaoProxy2 = (UserDao) Proxy.newProxyInstance(spring5test.class.getClassLoader(), UserDaoImpl.class.getInterfaces(), new Handler(userDao));
        System.out.println(userDaoProxy.insert());
        System.out.println(userDaoProxy.find());
        System.out.println("==================================");
        System.out.println(userDaoProxy2.insert());
        System.out.println(userDaoProxy2.find());


    }

    class Handler implements InvocationHandler {

        //要代理的原始对象
        private Object obj;

        public Handler(Object obj) {
            this.obj = obj;
        }

        /**
         * 在代理实例上处理方法调用并返回结果
         *
         * @param proxy  代理类
         * @param method 被代理的方法
         * @param args   该方法的参数数组
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName() + "方法执行前执行了.............");
            String res = (String) method.invoke(obj, args);
            System.out.println(method.getName() + "方法执行后执行了.............");
            return res;
        }
    }

    /**
     * 方法二 ：cglib实现动态代理
     */

    @Test
    public void cglibProxy() {

        ApplicationContext context = new AnnotationConfigApplicationContext(Spring5Config.class);
        UserDao userDao = context.getBean("userDaoImpl1", UserDao.class);


//        使用cglib创建代理类，cglib运行时，动态创建目标类子类(代理类），所以目标类不能使final的 public final class ...
//        (1)创建核心类
        Enhancer enhancer = new Enhancer();
//        (2)设置父类
        enhancer.setSuperclass(UserDao.class);
        // 3.3代理类方法将调用回调函数，等效JDK InvocationHandler接口。callback，子接口MethodInterceptor对方法进行增强的。
        enhancer.setCallback(new MethodInterceptor() {
            @Override
//          前三个参数:与idk动态代理invoke相同的
//          参数4:methodProxy ，方法代理
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName() + "方法执行前执行了.............");
                String res = (String) method.invoke(userDao, args);
                System.out.println(method.getName() + "方法执行后执行了.............");
                return res;
            }
        });

        UserDao userDaoProxy = (UserDao) enhancer.create();
        userDaoProxy.find();

    }

    /**
     * 测试 Aspect注解增强类
     */
    @Test
    public void aspectTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotationBeanTest.xml");
        UserDaoImpl userDaoImpl = context.getBean("userDaoImpl1", UserDaoImpl.class);
        System.out.println(userDaoImpl.update());
    }

    /**
     * xml配置 实现aspect增强
     */
    @Test
    public void aspectXmlTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aspectBeanTest.xml");
        UserDaoImpl userDaoImpl = context.getBean("userDao", UserDaoImpl.class);
        System.out.println(userDaoImpl.update());
    }


    /**
     * 纯注解实现aspect增强
     */
    @Test
    public void aspectAnnoTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spring5Config.class);
        UserDaoImpl userDaoImpl = context.getBean("userDaoImpl1", UserDaoImpl.class);
        System.out.println(userDaoImpl.update());
    }
}
