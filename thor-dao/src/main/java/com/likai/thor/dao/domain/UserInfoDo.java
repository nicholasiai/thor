package com.likai.thor.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

//@Data
public class UserInfoDo {

    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public UserInfoDo(Integer id, String userName, String userAccount, String password, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.userName = userName;
        this.userAccount = userAccount;
        this.password = password;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}