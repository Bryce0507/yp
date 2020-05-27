package com.yp.dto.wx;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class WxBindDTO {


    /**
     * 微信openId
     */
    @NotNull
    private String openId;

    /**
     * 微信昵称
     */
    @NotNull
    @Length(min = 1,max = 50 ,message = "微信昵称最大长度为50")
    private String nickName;

    /**
     * 微信头像地址
     */
    @NotNull
    @Length(min = 1,max = 1000 ,message = "头像地址最大长度为1000")
    private String headimgurl;

    /**
     * 性别
     */
    @NotNull
    @Length(min = 1,max = 1 ,message = "性别长度为1")
    private String sex;
    /**
     *  地址
     */
    @NotNull
    @Length(min = 1,max = 500 ,message = "地址最大长度1-500")
    private String address;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
