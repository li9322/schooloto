package com.li.entity;

import java.util.Date;

public class WechatAuth {
    /**
     * 主键
     */
    private Long wechatAuthId;

    /**
     * Wechat唯一标示
     */
    private String openId;
    private Date createTime;
    /**
     * 关联的用户信息（通过用户id）
     */
    private PersonInfo personInfo;

    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    @Override
    public String toString() {
        return "WechatAuth{" +
                "wechatAuthId=" + wechatAuthId +
                ", personInfo=" + personInfo +
                ", openId='" + openId + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
