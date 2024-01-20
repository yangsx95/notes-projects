package com.yangsx95.mybaitis.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.jdbc.DriverDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @author 杨顺翔
 * @since 2022/07/10
 */
public class FlywayH2MybatisTools {

    public static void initH2() throws Exception {
        // 加载配置文件
        Properties dbProperties = new Properties();
        dbProperties.load(Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config/db.properties")));
        // 配置flyway数据源，并初始化库信息
        DataSource dataSource = new DriverDataSource(FlywayH2MybatisTools.class.getClassLoader(),
                dbProperties.getProperty("h2.driver"),
                dbProperties.getProperty("h2.url"),
                dbProperties.getProperty("h2.username"),
                dbProperties.getProperty("h2.password"));
        Flyway.configure()
                .dataSource(dataSource)
                .load()
                .migrate();
    }

    public static <T> T getMapper(String configPath, Class<T> mapperClass) {
        try {
            initH2();
            InputStream in = Resources.getResourceAsStream(configPath);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            /*设置自动提交事务*/
            SqlSession session = factory.openSession(true);
            return session.getMapper(mapperClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
