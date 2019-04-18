package com.pms.service;

import com.pms.domain.User;

public interface UserService {
    User loginCheck(User user);

    User getUser(Integer id);

    int updateUser(User user);

    User register(User user);
}
