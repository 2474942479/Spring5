IOC 实现原理 工厂模式  通过xml配置文件获取类的全路径  再根据反射的Class.forName(全路径)获取类的class文件,再通过class，newInstance()实例化对象  从而实现解耦。
1、IOC思想基于IOC容器完成，IOC容器底层就是对象工厂J


(1)BeanFactory:IOC容器基本实现，是Spring内部的使用接口，不提供开发人员进行使用

*加载配置文件时候不会创建对象，在获取对象（使用）才去创建对象


(2)ApplicationContext:BeanFactory,接口的子接口，提供更多更强大的功能，一般由开发人员进行使用

*加载配置文件时候就会把在配置文件对象进行创建·



