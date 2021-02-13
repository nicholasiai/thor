package com.likai.thor.service.user;

import com.likai.thor.dao.domain.UserInfoDo;

/**
 * 用户信息操作类
 */
public interface UserManager {


    /**
     * 根据账户获取用户信息
     * @return
     */
    UserInfoDo getInfoByAccount(String userAccount);
}
