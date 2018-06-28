package com.newswebsite.newswebsite.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User_record {
    @Id
    @GeneratedValue
    private Integer userID;
    private Integer newsID;
    private String time;

    public User_record(){}

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User_record(Integer userID,Integer newsID,String time){
        this.userID=userID;
        this.newsID=newsID;
        this.time=time;
    }
}
