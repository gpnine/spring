package com.zcl.study;

import com.zcl.study.spring.model.User;
import com.zcl.study.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class SpringStudyApplicationTests {

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
//		System.out.println(jedisPool);
//		// 在连接池中得到jedis连接
//		Jedis jedis = jedisPool.getResource();
//		jedis.set("haha","你好");
//		jedis.set("name","zcl");
//		// 关闭当前连接
//		jedis.close();
    }

    @Test
    void t1() {
        String result = userService.getString("name");
        System.out.println(result);
    }

    @Test
    void t2() {
        String key = "testKey";
        String value = "测试数据";
        userService.expire(key, value);
    }

    @Test
    void t3() {
        User user = userService.selectById("1001");
        System.out.println(user);
    }

    @Test
    void l1() {
        String result = (String) userService.lettuceGetString("redisStr");
        System.out.println(result);
    }

    @Test
    void l2() {
        userService.lettuceExpire("redisStr", "asdf");
    }

    @Test
    void l3() {
        User user = userService.lettuceSelectById("1003");
        System.out.println(user);
    }
}
