package com.zzy.relation.entity;


public class Sales{

    private Integer id;
    private String did;
    private String contnt;
    private java.util.Date createTime;

    private String dtitle;
    private String cname;

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

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
    public void setDid(String value) {
        this.did = value;
    }
    public String getDid() {
       return this.did;
    }
    public void setContnt(String value) {
        this.contnt = value;
    }
    public String getContnt() {
       return this.contnt;
    }
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    public java.util.Date getCreateTime() {
       return this.createTime;
    }
}
