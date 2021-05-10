package com.zcl.study.spring.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zcl.study.spring.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
@Repository
@PropertySource("classpath:/db.properties")
public class PersonDao implements EmbeddedValueResolverAware {
    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String pwd;
    private String driverClass;
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        try {
            dataSource.setDriverClass(this.driverClass);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        this.dataSource = dataSource;
    }

    public String getPersonName() {
        String sql = "SELECT name FROM user WHERE id = 1";
        Connection connection;
        Statement stmt;
        String name = "";
        try {
            connection = dataSource.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public int addPerson(Person person) {
        String name = person.getName();
        String sql = "insert into user (name) value('" + name + "')";
        Connection connection;
        Statement stmt;
        try {
            connection = dataSource.getConnection();
            stmt = connection.createStatement();
            int i = stmt.executeUpdate(sql);
            if (i == 1) {
                System.out.println(name + "插入成功");
            }
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(name + "插入失败");
        return 0;
    }

    public boolean deletePerson(String id) {
        String sql = "delete from user where id = " + id;
        Connection connection;
        Statement stmt;
        try {
            connection = dataSource.getConnection();
            stmt = connection.createStatement();
            int i = stmt.executeUpdate(sql);
            if (i == 1) {
                System.out.println(id + "刪除成功");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(id + "刪除失败");
        return false;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.driverClass = stringValueResolver.resolveStringValue("${db.driverClass}");
    }

}
