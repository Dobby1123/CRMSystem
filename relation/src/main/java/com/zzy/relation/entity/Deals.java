package com.zzy.relation.entity;


public class Deals{

    private Integer id;
    private String title;
    private String content;
    private String dealtime;
    private String cid;
    private java.util.Date createTime;

    private String cname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setId(Integer value) {
        this.id = value;
    }
    public Integer getId() {
       return this.id;
    }
    public void setTitle(String value) {
        this.title = value;
    }
    public String getTitle() {
       return this.title;
    }
    public void setContent(String value) {
        this.content = value;
    }
    public String getContent() {
       return this.content;
    }
    public void setDealtime(String value) {
        this.dealtime = value;
    }
    public String getDealtime() {
       return this.dealtime;
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
