package com.pms.service.impl;

import com.pms.dao.UserKeyDao;
import com.pms.domain.UserKey;
import com.pms.service.UserKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserKeyServiceImpl implements UserKeyService {

    @Autowired
    private UserKeyDao userKeyDao;

    @Override
    public List<UserKey> getUserKeysByUserId(Integer userId) {
        return userKeyDao.selectUserKeysByUserId(userId);
    }

    @Override
    public int deleteUserKeyById(String id) {
        return userKeyDao.deleteUserKeyById(id);
    }
}
