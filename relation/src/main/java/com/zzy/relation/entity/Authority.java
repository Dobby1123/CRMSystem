package com.zzy.relation.entity;


import java.util.Date;

public class Authority{

    private Integer id;
    private String did;
    private String mid;
    private Date createTime;

    private String dname;
    private String mname;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setId(Integer value) {
        this.id = value;
    }
    public Integer getId() {
       return this.id;
    }
    public void setDid(String value) {
        this.did = value;
    }
    public String getDid() {
       return this.did;
    }
    public void setMid(String value) {
        this.mid = value;
    }
    public String getMid() {
       return this.mid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
