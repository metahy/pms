package com.pms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.pms.dao.ArithmeticDao;
import com.pms.dao.UserKeyDao;
import com.pms.domain.Arithmetic;
import com.pms.domain.UserKey;
import com.pms.domain.enums.ArithmeticEnum;
import com.pms.service.ArithmeticService;
import com.pms.util.AES;
import com.pms.util.DES;
import com.pms.util.PBE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Service
public class ArithmeticServiceImpl implements ArithmeticService {

    @Autowired
    private ArithmeticDao arithmeticDao;
    @Autowired
    private UserKeyDao userKeyDao;


    @Override
    public File encode(MultipartFile file) throws IOException {
        File fileResponse = new File("temp.txt");
        FileOutputStream fileResponseOutputStream = new FileOutputStream(fileResponse);
        InputStream fileInputStream = file.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String lineTxt;
        Arithmetic arithmetic = arithmeticDao.selectUsingArithmetic();
        if (arithmetic != null) {
            switch (ArithmeticEnum.valueOf(arithmetic.getName())) {
                case AES:
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        fileResponseOutputStream.write(Objects.requireNonNull(AES.desEncryption(lineTxt, arithmetic.getSecretKey())).getBytes());
                        fileResponseOutputStream.write('\r');
                    }
                    break;
                case DES:
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        fileResponseOutputStream.write(Objects.requireNonNull(DES.desEncryption(lineTxt, arithmetic.getSecretKey())).getBytes());
                        fileResponseOutputStream.write('\r');
                    }
                    break;
                case PBE:
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        fileResponseOutputStream.write(Objects.requireNonNull(PBE.desEncryption(lineTxt, arithmetic.getSecretKey())).getBytes());
                        fileResponseOutputStream.write('\r');
                    }
                    break;
            }
        }
        fileResponseOutputStream.close();
        return fileResponse;
    }

    @Override
    public UserKey encode(UserKey userKey) {
        switch (userKey.getArithmeticId()) {
            case 1:
                userKey.setResult(AES.desEncryption(userKey.getKeyWord(), userKey.getKeySecret()));
                break;
            case 2:
                userKey.setResult(DES.desEncryption(userKey.getKeyWord(), userKey.getKeySecret()));
                break;
            case 3:
                userKey.setResult(PBE.desEncryption(userKey.getKeyWord(), userKey.getKeySecret()));
                break;
        }
        if (userKeyDao.selectUserKeyByInfo(userKey) == null) {
            userKeyDao.insertUserKey(userKey);
        }
        return userKey;
    }

    @Override
    public File decode(MultipartFile file, String secretKey) throws IOException {
        File fileResponse = new File("temp.txt");
        FileOutputStream fileResponseOutputStream = new FileOutputStream(fileResponse);
        InputStream fileInputStream = file.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        Arithmetic arithmetic = arithmeticDao.selectUsingArithmetic();
        if (arithmetic != null) {
            switch (ArithmeticEnum.valueOf(arithmetic.getName())) {
                case AES:
                    checkNull(secretKey, fileResponseOutputStream, bufferedReader);
                    break;
                case DES:
                    checkNull(secretKey, fileResponseOutputStream, bufferedReader);
                    break;
                case PBE:
                    checkNull(secretKey, fileResponseOutputStream, bufferedReader);
                    break;
            }
        }
        fileResponseOutputStream.close();
        return fileResponse;
    }

    @Override
    public List<Arithmetic> getAllArithmetic() {
        return arithmeticDao.selectAllArithmetics();
    }

    @Override
    @Transactional
    public int useArithmetic(Integer id) {
        Arithmetic usingArithmetic = arithmeticDao.selectUsingArithmetic();
        arithmeticDao.updateArithmeticInUse(usingArithmetic.getId(), false);
        return arithmeticDao.updateArithmeticInUse(id, true);
    }

    @Override
    public int updateSecretKey(Integer id, String secretKey) {
        return arithmeticDao.updateArithmeticSecretKey(id, secretKey);
    }

    @Override
    public int setArithmeticInUse(Integer id, String inUse) {
        return arithmeticDao.updateArithmeticInUse(id, StringUtils.equals(inUse, "1"));
    }

    @Override
    public List<Arithmetic> getInUseArithmetic() {
        return arithmeticDao.selectUsingArithmetics();
    }

    private void checkNull(String secretKey, FileOutputStream fileResponseOutputStream, BufferedReader bufferedReader) throws IOException {
        String lineTxt;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            String s = AES.desDecryption(lineTxt, secretKey);
            if (s != null) {
                fileResponseOutputStream.write(s.getBytes());
            } else {
                fileResponseOutputStream.write("解析失败".getBytes());
            }
            fileResponseOutputStream.write('\r');
        }
    }
}
