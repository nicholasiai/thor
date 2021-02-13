package com.likai.thor.service.user.impl;


import com.likai.thor.dao.domain.UserInfoDo;
import com.likai.thor.dao.mapper.UserInfoMapper;
import com.likai.thor.service.user.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDo getInfoByAccount(String userAccount) {
        log.info("用户信息获取查询  account: {}",userAccount);
        return userInfoMapper.selectByPrimaryKey(userAccount);
    }
}
