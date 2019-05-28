package com.li.entity;

import java.util.Date;

public class PersonInfo {
    private Long userId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    private Integer enableStates;
    private Integer userType;
    private Date CreateTime;
    private Date LastEditTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getEnableStates() {
        return enableStates;
    }

    public void setEnableStates(Integer enableStates) {
        this.enableStates = enableStates;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getLastEditTime() {
        return LastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        LastEditTime = lastEditTime;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", enableStates=" + enableStates +
                ", userType=" + userType +
                ", CreateTime=" + CreateTime +
                ", LastEditTime=" + LastEditTime +
                '}';
    }
}
