package io.github.yangsx95.notes.mybatis.dao.util;

import org.apache.ibatis.session.SqlSession;

/**
 * MybatisTemplate
 * Mybatis模板工具类
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 9:50
 */
public class MybatisTemplate {

    public static Object excute(MyBatisCallback callback) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Object o;
        try {
            o = callback.doInMyBatis(sqlSession);
            MyBatisUtil.commit(sqlSession);
        } finally {
            MyBatisUtil.close(sqlSession);
        }
        return o;
    }
}
