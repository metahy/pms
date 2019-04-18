package com.pms.domain;

public class UserKey {
    private Integer id;
    private String keyWord;
    private String keySecret;
    private Integer arithmeticId;
    private String result;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public Integer getArithmeticId() {
        return arithmeticId;
    }

    public void setArithmeticId(Integer arithmeticId) {
        this.arithmeticId = arithmeticId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserKey{" +
                "id=" + id +
                ", keyWord='" + keyWord + '\'' +
                ", keySecret='" + keySecret + '\'' +
                ", arithmeticId=" + arithmeticId +
                ", result='" + result + '\'' +
                ", userId=" + userId +
                '}';
    }
}
