                                        一  IOC
IOC 实现原理 工厂模式  通过xml配置文件获取类的全路径  再根据反射的Class.forName(全路径)获取类的class文件,再通过class.newInstance()实例化对象  从而实现解耦。
1、IOC思想基于IOC容器完成，IOC容器底层就是对象工厂

    (1)BeanFactory:IOC容器基本实现，是Spring内部的使用接口，不提供开发人员进行使用
    *加载配置文件时候不会创建对象，在获取对象（使用）才去创建对象
    
    (2)ApplicationContext:BeanFactory,接口的子接口，提供更多更强大的功能，一般由开发人员进行使用
    *加载配置文件时候就会把在配置文件对象进行创建·

1、生命周期:从对象创建到对象销毁的过程

    (1）通过构造器创建bean实例（无参数构造)
    (2）为bean的属性设置值和对其他bean引用（调用set方法）
    (3）调用bean的初始化的方法（需要进行配置初始化的方法）,
    (4) bean可以使用了对象获取到了)
    (5）当容器关闭时候，调用bean的销毁的方法（需要进行配置销毁的方法）

                                    二    AOP
1、什么是AOP

    (1）面向切面编程（方面)，利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
    (2）通俗描述:不通过修改源代码方式，在主干功能里面添加新功能

2 AOP〔底层原理）

    AOP底层使用动态代理 ,有两种情况动态代理
        第一种有接口情况，使用JDK动态代理。 创建接口实现类代理对象，增强类方法
        第二种没有接口情况，使用CGLIB动态代理。创建子类的代理对象，增强类的方法
3 操作术语

    (1)、连接点
    类里面哪些方法可以被增强，这些方法称为连接点
    (2)、切入点
    实际被真正增强的方法，称为切入点
    (3)、通知（增强)
        1> 实际增强的逻辑部分称为通知（增强）
        2> 通知有5种类型 前置通知 后置通知 环绕通知  异常通知 最终通知(类似finally)
    (4)、切面 (是动作) : 把通知应用到切入点过程

4 AOP操作

    (1)、Spring框架一般都是基于Aspect实现AOP操作
        Aspect不是Spring 组成部分，独立AOP框架，一般把Aspect和Spirmg.框架一起使用，进行AOP操作
    (2)、基于AspectJ实现AOP操作
        (1）基于xml配置文件实现
        (2）基于注解方式实现（使用）。
    (3)、切入点表达式
        (1）切入点表达式作用:知道对哪个类里面的哪个方法进行增强
        (2）语法结构:  execution([权限修饰符][返回类型][类金路行][方法名称]([参数列表]))
        
            修饰符可省略
            举例1:对edu.zsq.springtest.dao.UserDao类里面的 insert进行增强
            execution(* edu.zsq.springtest.dao.UserDao.insert(..))
            举例2:对edu.zsq.springtest.dao.UserDao类里面的所有的方法进行增强
            execution(* edu.zsq.springtest.dao.UserDao.*(..))
            举例3：对edu.zsq.springtest.dao包里面所有类，类里面所有方法进行增强：
            execution(* edu.zsq.springtest.dao.*.*(..))
    (4) 相同的切入点抽取
        申明目标类切入点范围
    　　　 1.方法必须private，没有返回值，没有参数
                @Pointcut("execution(* edu.zsq.springtest.dao.impl.UserDaoImpl.update(..))")
                public void pointCut(){}
    　　　 2.之后使用将其当成方法调用。例如：@After("pointCut()")
    (5)有多个增强类多同一个方法进行增强，设置增强类优先级
       在增强类上面添加注解@Order(数字类型值) 数字越小  优先级越高
　