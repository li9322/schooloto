package com.li.entity;

import java.util.Date;

public class LocalAuth {
    /**
     * 主键
     */
    private Long localAuthId;

    private String userName;
    private String passWord;
    private Date createTime;
    private Date lastEditTime;

    /**
     * 关联的用户信息（通过用户id）
     */
    private PersonInfo personInfo;

    public Long getLocalAuthId() {
        return localAuthId;
    }

    public void setLocalAuthId(Long localAuthId) {
        this.localAuthId = localAuthId;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
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
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", personInfo=" + personInfo +
                '}';
    }
}
