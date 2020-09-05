package edu.zsq.springtest.dao.impl;

import edu.zsq.springtest.dao.UserDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author 张
 * 在注解里面value属性值可以省略不写，
 * 默认值是类名称，首字母小司 UserDao -> userDao
 */
@Repository(value = "userDaoImpl1")
public class UserDaoImpl implements UserDao {
    @Override
    public String insert() {
        System.out.println("Dao开始插入数据库");
        return "插入成功";
    }

    @Override
    public String find() {
        System.out.println("Dao开始查询数据库");
        return "查询成功";
    }

    /**
     * 通过Aop cglib增强该方法
     * @return
     */
    public String update(){
        System.out.println("Dao开始更新数据库");
        return "更新完毕";
    }
}
