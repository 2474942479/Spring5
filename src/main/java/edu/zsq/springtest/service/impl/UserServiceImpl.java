package edu.zsq.springtest.service.impl;

import edu.zsq.springtest.dao.UserDao;
import edu.zsq.springtest.service.UserService;

/**
 * @author 张
 */
public class UserServiceImpl implements UserService {
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
