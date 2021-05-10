package com.zcl.study.spring.memcached;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-9 .
 */
public class MemcachedJava {
    public static void main(String[] args) {
        try {
            // 本地连接 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("192.168.206.8", 11211));
            System.out.println("Connection to server sucessful.");

            // 存储数据
            Future fo = mcc.set("runoob", 900, "Free Education");
            // 添加新的 key
            mcc.replace("runoob", 900, "Largest Tutorials' Library");
            mcc.append("runoob", "asdf");
            mcc.prepend("runoob", "====");
            // 查看存储状态
            System.out.println("set status:" + fo.get());

            // 输出值
            System.out.println("runoob value in cache - " + mcc.get("runoob"));

            CASValue casValue = mcc.gets("runoob");
            System.out.println(casValue);

            CASResponse casr = mcc.cas("runoob", casValue.getCas(), 900, "Free Library");
            System.out.println(casr);
            System.out.println("runoob value in cache - " + mcc.get("runoob"));
            fo = mcc.delete("runoob");
            // 输出执行 delete 方法后的状态
            System.out.println("delete status:" + fo.get());

            // 获取键对应的值
            System.out.println("runoob value in cache - " + mcc.get("codingground"));


            mcc.set("number", 900, "123");
            mcc.incr("number", 1);
            System.out.println(mcc.get("number"));
            mcc.decr("number", 4);
            System.out.println(mcc.get("number"));
            // 关闭连接
            mcc.shutdown();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
