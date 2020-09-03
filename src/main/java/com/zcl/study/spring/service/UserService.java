package com.zcl.study.spring.service;

import com.zcl.study.spring.model.User;

/**
 * @author Chenglin Zhu
 * @date 2020/6/3
 */
public interface UserService {

    String getString(String key);

    void expire(String key, String value);

    User selectById(String id);

    Object lettuceGetString(String key);

    void lettuceExpire(String key, String value);

    User lettuceSelectById(String id);

    String getCode(String key);

    String login(String phone, String password, String code);
}
