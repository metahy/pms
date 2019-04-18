package com.pms.service;

import com.pms.domain.UserKey;

import java.util.List;

public interface UserKeyService {
    List<UserKey> getUserKeysByUserId(Integer userId);

    int deleteUserKeyById(String id);
}
