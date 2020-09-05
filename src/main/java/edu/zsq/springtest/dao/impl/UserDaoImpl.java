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
        System.out.println("Dao开始查询数据库");
        return "dao返回的查询结果";
    }
}
