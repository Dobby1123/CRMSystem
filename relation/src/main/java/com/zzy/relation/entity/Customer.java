package com.zzy.relation.entity;


public class Customer{

    private Integer id;
    private String name;
    private String phone;
    private String address;
    private String industry;
    private String type;
    private String aid;
    private String status;
    private java.util.Date createTime;

    private String atitle;

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
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
    public void setAddress(String value) {
        this.address = value;
    }
    public String getAddress() {
       return this.address;
    }
    public void setIndustry(String value) {
        this.industry = value;
    }
    public String getIndustry() {
       return this.industry;
    }
    public void setType(String value) {
        this.type = value;
    }
    public String getType() {
       return this.type;
    }
    public void setAid(String value) {
        this.aid = value;
    }
    public String getAid() {
       return this.aid;
    }
    public void setStatus(String value) {
        this.status = value;
    }
    public String getStatus() {
       return this.status;
    }
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    public java.util.Date getCreateTime() {
       return this.createTime;
    }
}
