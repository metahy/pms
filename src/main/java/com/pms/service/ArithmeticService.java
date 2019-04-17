package com.pms.service;

import com.pms.domain.Arithmetic;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ArithmeticService {
    File encode(MultipartFile file) throws IOException;

    File decode(MultipartFile file, String secretKey) throws IOException;

    List<Arithmetic> getAllArithmetic();

    int useArithmetic(Integer id);

    int updateSecretKey(Integer id, String secretKey);
}
