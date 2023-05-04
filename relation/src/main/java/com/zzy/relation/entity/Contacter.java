package com.zzy.relation.entity;


public class Contacter{

    private Integer id;
    private String name;
    private String phone;
    private String position;
    private String depart;
    private String wx;
    private String qq;
    private String email;
    private String cid;
    private java.util.Date createTime;

    private String ctname;

    public String getCtname() {
        return ctname;
    }

    public void setCtname(String ctname) {
        this.ctname = ctname;
    }

    public void setId(Integer value) {
        this.id = value;
    }
    public Integer getId() {
       return this.id;
    }
    public void setName(String value) {
        this.name = value;
    }
    public String getName() {
       return this.name;
    }
    public void setPhone(String value) {
        this.phone = value;
    }
    public String getPhone() {
       return this.phone;
    }
    public void setPosition(String value) {
        this.position = value;
    }
    public String getPosition() {
       return this.position;
    }
    public void setDepart(String value) {
        this.depart = value;
    }
    public String getDepart() {
       return this.depart;
    }
    public void setWx(String value) {
        this.wx = value;
    }
    public String getWx() {
       return this.wx;
    }
    public void setQq(String value) {
        this.qq = value;
    }
    public String getQq() {
       return this.qq;
    }
    public void setEmail(String value) {
        this.email = value;
    }
    public String getEmail() {
       return this.email;
    }
    public void setCid(String value) {
        this.cid = value;
    }
    public String getCid() {
       return this.cid;
    }
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    public java.util.Date getCreateTime() {
       return this.createTime;
    }
}
