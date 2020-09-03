package com.zcl.study.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Chenglin Zhu
 * @date 2020/6/3
 */
@Configuration
public class JedisUtil {
    @Autowired
    private JedisPool jedisPool;

    public void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }
}
