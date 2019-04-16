package com.pms.domain;

public class User {
    private Integer id;
    private String realName;
    private String userName;
    private String password;
    private Boolean admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
