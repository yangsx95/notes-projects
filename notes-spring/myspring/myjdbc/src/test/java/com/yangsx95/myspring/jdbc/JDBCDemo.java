package com.yangsx95.myspring.jdbc;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/8/25
 */
public class JDBCDemo {
    
    @Test
    public void jdbc() throws Exception {
        // 加载驱动
        Class.forName("org.h2.Driver");
        // 获取连接对象
        // Connection connection = DriverManager.getConnection("jdbc:h2:./data/test")d;
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:./.data/test");
        Connection connection = dataSource.getConnection();
        // 创建语句集
        PreparedStatement ps = connection.prepareStatement("select * from PUBLIC.TEST");
        // 执行sql获取resultSet
        ResultSet resultSet = ps.executeQuery();
        // 遍历结果集
        while (resultSet.next()) {
            System.out.println(resultSet.getString("ID") + "--" + resultSet.getString("NAME") + "--" + resultSet.getInt("AGE"));
        }

        resultSet.close();
        ps.close();
        connection.close();
    }
}
