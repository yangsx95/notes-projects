package io.github.yangsx95.notes.mybatis.complexsql.mapper;

import io.github.yangsx95.notes.mybatis.complexsql.mapper.HusbandAndWifeMapper;
import io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one1.HusbandAndWife;
import io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one2.Wife;
import io.github.yangsx95.notes.mybaitis.common.FlywayH2MybatisTools;
import org.junit.Test;

import java.util.List;

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