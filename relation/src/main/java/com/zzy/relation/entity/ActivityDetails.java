package com.zzy.relation.entity;

import java.math.BigDecimal;

/**
 * author zzy
 */
public class ActivityDetails {
    private int id;
    private BigDecimal money;
    private String type;
    private String address;
    private byte[] pic;
    private String aid;
    private String uid;
//    private String aname;
//    private String uname;

    public ActivityDetails(int id, BigDecimal money, String type, String address, byte[] pic, String aid, String uid) {
        this.id = id;
        this.money = money;
        this.type = type;
        this.address = address;
        this.pic = pic;
        this.aid = aid;
        this.uid = uid;
    }

//    public ActivityDetails(int id, BigDecimal money, String type, String address, byte[] pic, String aid, String uid, String aname, String uname) {
//        this.id = id;
//        this.money = money;
//        this.type = type;
//        this.address = address;
//        this.pic = pic;
//        this.aid = aid;
//        this.uid = uid;
//        this.aname = aname;
//        this.uname = uname;
//    }


    public ActivityDetails() {

    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

//    public String getAname() {
//        return aname;
//    }
//
//    public void setAname(String aname) {
//        this.aname = aname;
//    }
//
//    public String getUname() {
//        return uname;
//    }
//
//    public void setUname(String uname) {
//        this.uname = uname;
//    }
}
