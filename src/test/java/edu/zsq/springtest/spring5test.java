package edu.zsq.springtest;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import edu.zsq.springtest.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class spring5test {


    @Test
    public void test() {

//        IOC 实现原理: 工厂模式  通过xml配置文件获取类的全路径  再根据反射的Class.forName("全路径")获取类的class文件,
//        再通过class，newInstance()实例化对象  从而实现解耦
//        BeanFactory：IOC容器基本实现,


//        加载配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        获取配置创建的对象，并转换类型
        User user = context.getBean("user", User.class);
        String id = user.getId();
        System.out.println(id+":"+user.getName());

    }

}
