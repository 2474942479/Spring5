package edu.zsq.springtest.service.impl;

import edu.zsq.springtest.dao.UserDao;
import edu.zsq.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Transactional 开启事务注解
 * @author 张
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * 根据类型注入  添加注解后可以不用加set方法
     * @Qualifier 配合@Autowired使用，指定具体实现类
     */
    @Autowired
    @Qualifier(value = "userDaoImpl1")
    private UserDao userDao;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public String add() {
        System.out.println("开始调用service添加方法");
        return userDao.insert();
    }


}
