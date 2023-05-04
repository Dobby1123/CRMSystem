package com.zzy.relation.entity;


public class Activity{

    private Integer id;
    private String title;
    private String content;
    private String times;
    private String status;
    private java.util.Date createTime;

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
    public void setTimes(String value) {
        this.times = value;
    }
    public String getTimes() {
       return this.times;
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
