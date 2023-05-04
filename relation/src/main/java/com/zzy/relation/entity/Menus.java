package com.zzy.relation.entity;


public class Menus{

    private Integer id;
    private String icons;
    private String name;
    private String url;
    private java.util.Date createTime;

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setId(Integer value) {
        this.id = value;
    }
    public Integer getId() {
       return this.id;
    }
    public void setIcons(String value) {
        this.icons = value;
    }
    public String getIcons() {
       return this.icons;
    }
    public void setName(String value) {
        this.name = value;
    }
    public String getName() {
       return this.name;
    }
    public void setUrl(String value) {
        this.url = value;
    }
    public String getUrl() {
       return this.url;
    }
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    public java.util.Date getCreateTime() {
       return this.createTime;
    }
}
