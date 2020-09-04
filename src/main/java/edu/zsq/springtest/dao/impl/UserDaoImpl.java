package edu.zsq.springtest.dao.impl;

import edu.zsq.springtest.dao.UserDao;

/**
 * @author 张
 */
public class UserDaoImpl implements UserDao {
    @Override
    public String insert() {
        System.out.println("Dao开始查询数据库");
        return "dao返回的查询结果";
    }
}
