package com.pms.dao;

import com.pms.domain.Arithmetic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArithmeticDao {
    Arithmetic selectUsingArithmetic();

    List<Arithmetic> selectAllArithmetics();

    int updateArithmeticInUse(@Param("id") Integer id, @Param("inUse") Boolean inUse);

    int updateArithmeticSecretKey(@Param("id")Integer id, @Param("secretKey") String secretKey);
}
