package com.pms.dao;

import com.pms.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User selectUserByUserName(String userName);
}
