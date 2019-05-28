package com.li.entity;

import java.util.Date;

public class LocalAuth {
    private Long localAuthId;
    private Long userId;
    private String userName;
    private String passWord;
    private Date createTime;
    private Date lastEditTime;

    public Long getLocalAuthId() {
        return localAuthId;
    }

    public void setLocalAuthId(Long localAuthId) {
        this.localAuthId = localAuthId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    @Override
    public String toString() {
        return "LocalAuth{" +
                "localAuthId=" + localAuthId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                '}';
    }
}
