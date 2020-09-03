package com.zcl.study.spring.service.impl;

import com.zcl.study.spring.model.User;
import com.zcl.study.spring.service.UserService;
import com.zcl.study.spring.util.JedisUtil;
import com.zcl.study.spring.util.KeyNameUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author Chenglin Zhu
 * @date 2020/6/3
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> stringOperations;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    /**
     * redis String 类型
     * 需求：用户输入一个key
     * 先判断redis中是否存在该数据
     * 如果存在则在redis中查询
     * 如果不存在，在mysql中查询
     */
    @Override
    public String getString(String key) {
        // 1、得到jedis对象
        Jedis jedis = jedisUtil.getJedis();
        String val;
        // 2、判断key是否存在
        if (jedis.exists(key)) {
            LOGGER.info("查询redis中的数据");
            val = jedis.get(key);
        } else {
            val = "java";
            LOGGER.info("查询Mysql中数据：{}", val);
            jedis.set(key, val);
        }
        // 3、关闭连接
        jedisUtil.close(jedis);
        return val;
    }

    @Override
    public void expire(String key, String value) {
        Jedis jedis = jedisUtil.getJedis();
        jedis.set(key, value);
        jedis.expire(key, 20);
        jedis.close();
    }

    @Override
    public User selectById(String id) {
        String key = "user:" + id;
        Jedis jedis = jedisUtil.getJedis();
        User user = new User();
        if (jedis.exists(key)) {
            LOGGER.info("查询redis数据");
            Map<String, String> map = jedis.hgetAll(key);
            user.setId(map.get("id"));
            user.setName(map.get("name"));
            user.setAge(Integer.parseInt(map.get("age")));
        } else {
            LOGGER.info("查询mysql数据库");
            user.setId(id);
            user.setName("刘德华");
            user.setAge(49);
            Map<String, String> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            map.put("age", user.getAge() + "");
            jedis.hmset(key, map);
        }
        jedisUtil.close(jedis);
        return user;
    }

    @Override
    public String lettuceGetString(String key) {
        if (redisTemplate.hasKey(key)) {
            return stringOperations.get(key);
        } else {
            String val = "redisTemplate模板学习lettuce客户端";
            stringOperations.set(key, val);
            return val;
        }
    }

    @Override
    public void lettuceExpire(String key, String value) {
        stringOperations.set(key, value);
        redisTemplate.expire(key, 20, TimeUnit.SECONDS);
    }

    @Override
    public User lettuceSelectById(String id) {
        if (hashOperations.hasKey(KeyNameUtil.USER, id)) {
            return (User) hashOperations.get(KeyNameUtil.USER, id);
        } else {
            LOGGER.info("查询数据库");
            User user = new User();
            user.setId(id);
            user.setName("刘德华");
            user.setAge(40);
            /*
             * @param h 用户键实体 user
             * @param hk 用户主键 id
             * @param hv 整个对象
             */
            hashOperations.put(KeyNameUtil.USER, id, user);
            return user;
        }
    }

    @Override
    public String getCode(String phone) {
        String key = phone + ":code";
        String code = (int) ((Math.random() * 9 + 1) * 1000) + "";
        if (redisTemplate.hasKey(key)) {
            stringOperations.get(key);
        } else {
            stringOperations.set(key, code);
            redisTemplate.expire(key, 20, TimeUnit.SECONDS);
        }
        return code + "";

    }

    @Override
    public String login(String phone, String password, String code) {
        String correctPassword = "123";
        String key = phone + ":count";
        String psKey = phone + ":pwdCount";
        long counts = stringOperations.increment(key);
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        if (counts > 3) {
            return "登录超过三次，需要等待5分钟后重新登录";
        }
        if (redisTemplate.hasKey(phone + ":code")) {
            String vCode = stringOperations.get(phone + ":code");
            if (StringUtils.equals(vCode, code)) {
                if (StringUtils.equals(correctPassword, password)) {
                    return "登录成功";
                } else {
                    long pwdCounts = stringOperations.increment(psKey);
                    if (pwdCounts > 5) {
                        return "两分钟内仅运行输入错误密码5次,请1小时后再登录";
                    }
                    return "登录失败，密码不正确";
                }
            } else {
                return "验证码错误";
            }
        } else {
            return "登录失败，验证码失效";
        }
    }
}
