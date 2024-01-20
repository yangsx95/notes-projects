package io.github.yangsx95.notes.jdbc;

import oracle.jdbc.OracleDriver;
import org.junit.Test;

import java.sql.*;

/**
 * OracleTest
 * JDBC六大编程步骤，Oracle
 * <p>
 *
 * @author Feathers
 * @date 2018-05-22 14:15
 */
public class OracleTest {

    /**
     * oracle url 格式
     * jdbc:oracle:thin:@host:port:SID              jdbc:oracle:thin:@localhost:1521:orcl
     * jdbc:oracle:thin:@//host:port/service_name   jdbc:oracle:thin:@//localhost:1521/orcl.city.com
     * jdbc:oracle:thin:@TNSName                    jdbc:oracle:thin:@TNS_ALIAS_NAME
     */
    private static final String url = "jdbc:oracle:thin:@39.107.120.250:1523:orcl";
    private static final String user = "test";
    private static final String password = "test";

    @Test
    public void test() {
        Connection conn = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            // 1.加载驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // 2.获取连接
            DriverManager.registerDriver(new OracleDriver());
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
