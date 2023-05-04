package com.zzy.relation.entity;


import java.util.List;

public class User{

    private Integer id;
    private String phone;
    private String password;
    private String realname;
    private String sex;
    private String age;
    private String did;
    private java.util.Date createTime;
    private String username;

    private List<Menus> menus;

    private String dname;

    public List<Menus> getMenus() {
        return menus;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Integer value) {
        this.id = value;
    }
    public Integer getId() {
       return this.id;
    }
    public void setPhone(String value) {
        this.phone = value;
    }
    public String getPhone() {
       return this.phone;
    }
    public void setPassword(String value) {
        this.password = value;
    }
    public String getPassword() {
       return this.password;
    }
    public void setRealname(String value) {
        this.realname = value;
    }
    public String getRealname() {
       return this.realname;
    }
    public void setSex(String value) {
        this.sex = value;
    }
    public String getSex() {
       return this.sex;
    }
    public void setAge(String value) {
        this.age = value;
    }
    public String getAge() {
       return this.age;
    }
    public void setDid(String value) {
        this.did = value;
    }
    public String getDid() {
       return this.did;
    }
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    public java.util.Date getCreateTime() {
       return this.createTime;
    }
}
