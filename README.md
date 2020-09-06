一 IOC
    实现原理: 工厂模式  通过xml配置文件获取类的全路径  再根据反射的Class.forName(全路径)获取类的class文件,再通过class.newInstance()实例化对象  从而实现解耦。
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

二 AOP
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
       
三 事务      
1、什么是事务

    (1）事务是数据库操作最基本单元，逻辑上一组操作，要么都成功，如果有一个失败所有操作都失败
    (2）典型场景:银行转账
        lucy转账100元给mary
        露西转账100元给玛丽
2、事务四个特性(ACID) 

    (1）原子性: 过程中不可分割 逻辑上一组操作，要么都成功，要么都失败
    (2）一致性: 操作前后总量不变
    (3)隔离性：各个事务操作之间互不影响
    (4)持久性：事务提交后 表中数据发生变化
    

4、事务操作（Spring事务管理介绍）

    1)、事务添加到JavaEE三层结构里面Service层（业务逻辑层）
    2)、在Spring进行事务管理操作
        有两种方式:编程式事务管理和声明式事务管理(推荐)
    3)、声明式事务管理
       (1)基于注解方式(推荐)
       (2)基于xml配置文件方式
5、在Spring进行声明式事务管理，底层使用AOP原理实现了事务的添加

6、Spring开启声明式事务管理

    (1）提供一个接口(PlatformTransactionManager)，代表事务管理器，这个接口针对不同的框架提供不同的实现类。
    (2)xml开启事务管理添加并在service实现类上添加@Transactional注解
        @Transactional注解参数：    
            1、propagation:事务传播行为(默认REQUIRED)  多事务方法(对表中数据进行变化的操作 增删改)之间直接调用，这个过程中事务是如何管理的
                1).PROPAGATION_REQUIRED ,required ,必须使用事务  （默认值）
                    A 如果使用事务，B 使用同一个事务。（支持当前事务）
                    A 如果没有事务，B将创建一个新事务。
                2).PROPAGATION_SUPPORTS，supports ，支持事务
        　　　　　　　A 如果使用事务，B 使用同一个事务。（支持当前事务）
        　　　　　　　A 如果没有事务，B 将以非事务执行。
                3).PROPAGATION_MANDATORY，mandatory 强制
                    A 如果使用事务，B 使用同一个事务。（支持当前事务）
                    A 如果没有事务，B 抛异常
                4).PROPAGATION_REQUIRES_NEW ， requires_new ，必须是新事务
                    A 如果使用事务，B将A的事务挂起，再创建新的。
                    A 如果没有事务，B将创建一个新事务  
                5).PROPAGATION_NOT_SUPPORTED ，not_supported 不支持事务
                    A 如果使用事务，B将A的事务挂起，以非事务执行
                    A 如果没有事务，B 以非事务执行
                6).PROPAGATION_NEVER，never 从不使用
                    A 如果使用事务，B 抛异常
                    A 如果没有事务，B 以非事务执行 
                7).PROPAGATION_NESTED nested 嵌套
                    A 如果使用事务，B将采用嵌套事务。
     　　　　　　嵌套事务底层使用Savepoint 设置保存点，将一个事务，相当于拆分多个。比如业务A为AB两个曹祖，业务B为CD两个操作，业务AB使用同一个事务，在AB (POINT） CD，当业务B失败时，回滚到POINT处，从而业务A还是成功的，就是保持点的操作。
    　　　　　　　　底层使用嵌套try方式
            掌握：PROPAGATION_REQUIRED、PROPAGATION_REQUIRES_NEW、PROPAGATION_NESTED
            
            2、ioslation:事务隔离级别
                3个读问题：
                    脏读：读取到未提交的数据
                    不可重复读：读取到已经提交的数据(update)
                    幻读：读取到已经提交的数据(insert)
                
                解决隔离问题(4种隔离级别)
                    读未提交：存在三个问题。
                    读已提交：存在两个问题，解决脏读问题(Oracle默认级别)
                    可重复读：存在一个问题，解决脏读，不可重复读问题(Mysql默认级别)
                    串行化：解决所有问题。  
            3、timeout:超时时间
                (1）事务需要在一定时间内进行提交，如果不提交进行回滚↓
                (2）默认值是-1，设置时间以秒单位进行计算
            4、readOnly:是否只读
                (1）读:查询操作，写:添加修改删除操作·
                (2) readOnly_默认值false，表示可以查询，可以添加修改删除操作
                (3）设置readOnly值是true，设置成true之后，只能查询。
            5、rollbackFor:回滚·设置出现哪些异常进行事务回滚
            6、noRollbackFor:不回滚·设置出现哪些异常不进行事务回滚
四、Spring5 新特性
1、核心特性

        JDK8的增强:
        。访问Resuouce时提供getFile或和isFile防御式抽象
        。有效的方法参数访问基于java 8反射增强
        。在Spring核心接口中增加了声明default方法的支持一贯使用JDK┐Charset和StandardCharsets的增强
        。兼容JDK9
        . Spring 5.o框架自带了通用的日志封装
        。持续实例化via构造函数(修改了异常处理)
        .Spring 5.o框架自带了通用的日志封装
        . springjcl替代了通用的日志，仍然支持可重写
        。自动检测log4j 2.x,SLF4J,JUL(java.util.Logging）而不是其他的支持
        。访问Resuouce时提供getFile或和isFile防御式抽象
        。基于NIO的readableChannel也提供了这个新特性
        
2、核心容器

        。支持候选组件索引(也可以支持环境变量扫描)
        。支持@Nullable注解
            @Nullable注解可以使用在方法上面，属性上面，参数上面，表示方法返回可以为空，属性值可以为空，参数值可以为空

        。函数式风格GenericApplicationContext/AnnotationConfigApplicationContext
        。基本支持bean API注册
        。在接口层面使用CGLIB动态代理的时候，提供事物，缓存，异步注解检测
        .XML配置作用域流式
        .Spring WebMVC
        。全部的Servlet 3.1签名支持在Spring-provied Filter实现
        。在Spring MVC Controller方法里支持Servlet4.o PushBuilder参数
        。多个不可变对象的数据绑定(Kotlin/ Lombok/@ConstructorPorties)
        。支持jackson2.9
        。支持JSON绑定API
        。支持protobuf3
        。支持Reactorg.1Flux和Mono

3、Spring5框架新功能（Webflux) 

    1、SpringWebflux介绍(应用于网关)
        (1)是Spring5添加新的模块，用于web 开发的，功能和SpringMVC类似的，Webflux使用当前一种比较流程响应式编程出现的框架。
        (2)使用传统web框架，比如SpringMVC，这些基于Servlet,容器，Webflux是一种异步非阻塞的框架，异步非阻塞的框架在Servlet3.1以后才支持，核心是基于Reactor的相关API实现的
        (3）什么是异步非阻塞
            异发和同步 ： 异步和同步针对调用者，调用者发送请求，如果等着对方回应之后才去做其他事情就是同步，如果发送请求之后不等着对方回应就去做其他事情就是异步。
            非阻塞和阻塞：阻塞和非阻塞针对被调用者，被调用者受到请求之后，做完请求任务之后才给出反馈就是阻塞，受到请求之后马上给出反馈然后再去做事情就是非阻塞。
            上面都是针对对象不一样，
        (4)Webflux特点:
            第一 非阻塞式:在有限资源下，提高系统吞吐量和伸缩性，以Reactor为基础实现响应式编程.
            第二 函数式编程:Spring5框架基于java8，Webflux使用Java8函数式编程方式实现路由请求
        (5)与SpringMVC比较:
            第一 两个框架都可以使用注解方式，都运行在Tomet等容器中
            第二 SpringMVC采用命令式编程，Webflux采用异步响应式编程
    
    2、响应式编程
        (1)什么是响应式编程
            响应式编程是一种面向数据流和变化传播的编程范式。这意味着可以在编程语言中很方便地表达静态或动态的数据流，而相关的计算模型会自动将变化的值通过数据流进行传播。
            电子表格程序就是响应式编程的一个例子。单元格可以包含字面值或类似"=B1+C1"的公式，而包含公式的单元格的值会依据其他单元格的值的变化而变化。
        (2）Java8及其之前版本
            观察者模式(案例:军队哨兵,发现敌人,发出通知，做出响应)
            实现观察者模式的两个类 Observer和 Observable
        (3)、响应式编程（Reactor实现）
            (1  响应式编程操作中，Reactor是满足Reactive规范框架
            (2  Reactor有两个核心类，Mono和Flux，这两个类实现接口Publisher，提供丰富操作符。Flux对象实现发布者，返回N个元素;Mono实现发布者，返回0或者1个元素
            (3  Flux和Mono都是数据流的发布者，使用Flux和Mono都可以发出三种数据信号:元素值，错误信号，完成信号，错误信号和完成信号都代表终止信号，终止信号用于告诉订阅者数据流结束了，错误信号终止数据流同时把错误信息传递给订阅者
            (4  三种信号特点
                *错误信号和完成信号都是终止信号，不能共存的
                *如果没有发送任何元素值，而是直接发送错误或者完成信号，表示是空数据流
                *如果没有错误信号，没有完成信号，表示是无限数据流
            (5  调用just或者其他方法只是声明数据流，数据流并没有发出，只有进行订阅之后才会触发数据流，不订阅什么都不会发生的
                //just方法直接声明    subscribe()订阅
                Flux.just(1,2,3,4). subscribe(System.out::print);
                Mono.just(1).subscribe(System.out::print);
            (6  操作符(类似java8 新特性stream流的操作符): 对数据流进行一道道操作，称为操作符，比如工厂流水线·
                第一 map操作符 将元素映射为新元素
                第二 flatmap操作符 将每个元素映射为流,最终合并成一个大流  
                    
    3、SpringFlux执行流程:   SpringWebflux执行过程和 SpringMVC相似的
    
        SpringWebflux核心控制器DispatchHandler，实现接口WebHandler 
            *接口 WebHandler有一个方法: 
                Mono<Void> handle(ServerWebExchange var1);
                实现类: DispatcherHandler中的handle方法
                
                    public Mono<Void> handle(ServerWebExchange exchange) {  //  ServerWebExchange 放http请求响应信息
                            return this.handlerMappings == null ? this.createNotFoundError() : Flux.fromIterable(this.handlerMappings).concatMap((mapping) -> {
                                return mapping.getHandler(exchange);    //根据请求获取对应的Mapping(映射匹配)
                            }).next().switchIfEmpty(this.createNotFoundError()).flatMap((handler) -> {
                                return this.invokeHandler(exchange, handler);   //执行具体业务
                            }).flatMap((result) -> {
                                return this.handleResult(exchange, result);     //返回处理结果
                            });
                        }
        SpringWebflux核心组件
            *DispatcherHandler，负责请求的处理
            *HandlerMapping:请求查询到处理的方法
            *HandlerAdapter:真正负责请求处理
            *HandlerResultHandler:响应结果处理
            
        Spring Webflux实现函数式编程，实现两个接口:RourerFunction(路由处理)和HandlerFunction(处理函数）↓
        
    4、Spring Webflux(基于注解编程模型)
        说明:注解方式表面无太大区别，底层不一样
        SpringMVC方式实现，同步阻塞的方式，基于SpringMVC+Servlet+Tomcat.
        SpringWebflux:方式实现，异步非阻塞方式，基于SpringWebflux+Reactor+Netty.
    5、SpringWebflux（基于函数式编程模型）
        (1)在使用函数式编程模型操作时候，需要自己初始化服务器
        (2)基于函数式编程模型时候，有两个核心接口:RouterFunction(实现路由功能，请求转发
        给对应的handler）和HandlerFunction(处理请求生成响应的函数）。核心任务定义两个函数
        式接口的实现并且启动需要的服务器。
        (3) SpringWebflux 请求和响应不再是ServletRequest和 ServletResponse,，而是
        ServerReguest.和SerxerResponse
