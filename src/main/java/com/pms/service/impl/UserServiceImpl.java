package com.pms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.pms.dao.UserDao;
import com.pms.domain.User;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User loginCheck(User user) {
        User userDB = userDao.selectUserByUserName(user.getUserName());
        return userDB != null ? StringUtils.equals(userDB.getPassword(), user.getPassword()) ? userDB : null : null;
    }

    @Override
    public User getUser(Integer id) {
        return userDao.selectUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User register(User user) {
        return userDao.insertUser(user) > 0 ? userDao.selectUserByUserName(user.getUserName()) : null;
    }
}
