package com.yangsx95.demo.mapper;

import com.yangsx95.demo.pojo.one2one1.HusbandAndWife;
import com.yangsx95.demo.pojo.one2one2.Wife;
import com.yangsx95.mybaitis.common.FlywayH2MybatisTools;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.jdbc.DriverDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

/**
 * HusbandAndWifeMapperTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-26 22:05
 */
public class HusbandAndWifeMapperTest {

    private static final HusbandAndWifeMapper husbandAndWifeMapper
            = FlywayH2MybatisTools.getMapper("SqlMapConfig.xml", HusbandAndWifeMapper.class);

    @Test
    public void findAllHusbandAndWife() {
        List<HusbandAndWife> list = husbandAndWifeMapper.findAllHusbandAndWife();
        assertTrue(list.size() > 0);
        System.out.println(list);
    }

    @Test
    public void findAllHusbandAndWife2() {
        List<Wife> list = husbandAndWifeMapper.findAllHusbandAndWife2();
        assertTrue(list.size() > 0);
        System.out.println(list);
    }

}