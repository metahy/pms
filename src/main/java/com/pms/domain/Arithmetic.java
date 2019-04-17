package com.pms.domain;

public class Arithmetic {
    private Integer id;
    private String name;
    private String secretKey;
    private Boolean inUse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    @Override
    public String toString() {
        return "Arithmetic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", inUse=" + inUse +
                '}';
    }
}
