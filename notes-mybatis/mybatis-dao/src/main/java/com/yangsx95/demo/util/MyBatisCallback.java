package com.yangsx95.demo.util;

import org.apache.ibatis.session.SqlSession;

/**
 * MyBatisCallback
 * mybatis模板回调， 用于执行具体的操作
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 9:49
 */
public interface MyBatisCallback {
    Object doInMyBatis(SqlSession ses);
}
