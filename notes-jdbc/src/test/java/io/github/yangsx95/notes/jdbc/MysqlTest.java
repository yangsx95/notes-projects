package io.github.yangsx95.notes.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * MysqlTest
 * Jdbc六大编程步骤，连接mysql数据库
 * <p>
 *
 * @author Feathers
 * @date 2018-05-22 14:15
 */
public class MysqlTest {

    /**
     * jdbc mysql url
     * jdbc:mysql://[host:port],[host:port].../[database][?参数名1][=参数值1][&参数名2][=参数值2]...
     *
     * 参数：
     * user 数据库用户名（用于连接数据库）
     * password 用户密码（用于连接数据库）
     * useUnicode 是否使用Unicode字符集，如果参数characterEncoding设置为gb2312或gbk，本参数值必须设置为true
     * characterEncoding 当useUnicode设置为true时，指定字符编码。比如可设置为gb2312或gbk
     * autoReconnect 当数据库连接异常中断时，是否自动重新连接
     * autoReconnectForPools 是否使用针对数据库连接池的重连策略
     * failOverReadOnly 自动重连成功后，连接是否设置为只读？
     * maxReconnects autoReconnect设置为true时，重试连接的次数
     * initialTimeout autoReconnect设置为true时，两次重连之间的时间间隔，单位：秒
     * connectTimeout 和数据库服务器建立socket连接时的超时，单位：毫秒。 0表示永不超时，适用于JDK 1.4及更高版本
     * socketTimeout socket操作（读写）超时，单位：毫秒。 0表示永不超时
     */
    private static final String url = "jdbc:mysql://39.107.120.250:3306/test";
    private static final String user = "root";
    private static final String password = "root";

    @Test
    public void test() {
        Connection conn = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获取连接
            conn = DriverManager.getConnection(url, user, password);
            // 3.创建Statement对象
            statement = conn.createStatement();
            // 4.使用Statement对象执行sql
            resultSet = statement.executeQuery("select * from TEST_USER");
            // 5.如果执行查询语句，则操作结果集合
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6.关闭资源
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
