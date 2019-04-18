package com.pms.dao;

import com.pms.domain.UserKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserKeyDao {
    int insertUserKey(UserKey userKey);

    UserKey selectUserKeyByInfo(UserKey userKey);

    List<UserKey> selectUserKeysByUserId(Integer userId);

    int deleteUserKeyById(String id);
}
