package com.zcl.study;

import com.zcl.study.spring.model.ESingleton;
import com.zcl.study.spring.model.ESingleton1;
import com.zcl.study.spring.model.ESingleton2;
import com.zcl.study.spring.model.LanSingleton;
import com.zcl.study.spring.model.LanSingleton1;
import com.zcl.study.spring.model.LanSingleton2;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class TestSingleton {

    @Test
    public void testEhan() {
        ESingleton instance = ESingleton.INSTANCE;
        ESingleton instance2 = ESingleton.INSTANCE;
        System.out.println("方式一:" + instance);
        System.out.println("方式一:" + instance2);

        System.out.println("方式二:" + ESingleton1.INSANCE);
        System.out.println("方式三:" + ESingleton2.INSTANCE);
    }

    @Test
    public void testLanhan() {
        Callable<LanSingleton> callable = LanSingleton::getInstance;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<LanSingleton> newSingletonFuture = executorService.submit(callable);
        Future<LanSingleton> newSingletonFuture2 = executorService.submit(callable);
        try {
            LanSingleton instance = newSingletonFuture.get();
            LanSingleton instance2 = newSingletonFuture2.get();
            System.out.println("方式一:" + instance);
            System.out.println("方式一:" + instance2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("方式二:" + LanSingleton1.getInstance());
        System.out.println("方式二:" + LanSingleton1.getInstance());
        System.out.println("方式三:" + LanSingleton2.getInstance());
        System.out.println("方式三:" + LanSingleton2.getInstance());
    }

    @Test
    public void testObject() throws CloneNotSupportedException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 1、new一个对象
        User user = new User();
        user.setName("123");
        // 2、克隆对象，需要实现Cloneable接口，覆盖clone方法
        User user2 = (User) user.clone();
        System.out.println(user);
        System.out.println(user2);

        // 3、通过反射创建对象
        User user3 = User.class.newInstance();
        user3.setName("321");
        System.out.println(user3);

        User user4 = User.class.getConstructor().newInstance();
        user4.setName("222");
        System.out.println(user4);

        // 4、序列化一个对象

        File file = new File("user.obj");

        try (FileOutputStream os = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(os);
             FileInputStream is = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            oos.writeObject(user);
            User user5 = (User) ois.readObject();
            System.out.println(user5);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

