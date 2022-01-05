package com.xdclass.online_xdclass.service;

import com.xdclass.online_xdclass.model.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    int save(Map<String, String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findUserInfoByToken(Integer userId);

    //User findByPhone(String phone);
}
