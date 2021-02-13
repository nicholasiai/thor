package com.likai.thor.dao.mapper;

import com.likai.thor.dao.domain.UserInfoDo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoDo record);

    int insertSelective(UserInfoDo record);

    /**
     * 根据主键查询
     * @param userAccount
     * @return
     */
    UserInfoDo selectByPrimaryKey(String userAccount);

    int updateByPrimaryKeySelective(UserInfoDo record);

    int updateByPrimaryKey(UserInfoDo record);

}