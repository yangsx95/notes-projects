package com.yangsx95.demo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatisUtil
 * Mybatis模板工具类
 * 负责加载配置文件，创建SqlSessionFactory，获取SqlSession
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 9:48
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory == null ? null : sqlSessionFactory.openSession();
    }

    public static void close(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    public static void commit(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }
}
