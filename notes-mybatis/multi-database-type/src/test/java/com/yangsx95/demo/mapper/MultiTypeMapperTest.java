package com.yangsx95.demo.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * MultiTypeMapperTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 9:53
 */
public class MultiTypeMapperTest {

    private MultiTypeMapper multiTypeMapper;

    @Before
    public void prepare() throws Exception {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession(true);
        multiTypeMapper = session.getMapper(MultiTypeMapper.class);
    }

    @Test
    public void getTime() {
        String time = multiTypeMapper.getTime();
        System.out.println(time);
    }
}