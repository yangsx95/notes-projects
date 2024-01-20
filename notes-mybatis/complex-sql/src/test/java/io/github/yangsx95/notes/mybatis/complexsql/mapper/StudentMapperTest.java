package io.github.yangsx95.notes.mybatis.complexsql.mapper;

import io.github.yangsx95.notes.mybatis.complexsql.mapper.StudentMapper;
import io.github.yangsx95.notes.mybatis.complexsql.pojo.Student;
import io.github.yangsx95.notes.mybaitis.common.FlywayH2MybatisTools;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * StudentMapperTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 13:37
 */
public class StudentMapperTest {
    private static final StudentMapper studentMapper
            = FlywayH2MybatisTools.getMapper("SqlMapConfig.xml", StudentMapper.class);


    @Test
    public void findAll() {
        List<Student> list = studentMapper.findAll("赵");
        assertTrue(list.size() > 0);
        System.out.println(list);
    }

    @Test
    public void findAllStu() {
        List<Student> list = studentMapper.findAllStu("赵");
        assertTrue(list.size() > 0);
        System.out.println(list);
    }

    @Test
    public void findByIds() {
        List<Student> list = studentMapper.findByIds(new Integer[]{1, 2});
        System.out.println(list);
        assertTrue(list.size() > 0);
    }


    @Test
    public void findByIds1() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        List<Student> list = studentMapper.findByIdsList(ids);
        System.out.println(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void findByIdsMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("id1", 1);
        map.put("id2", 2);

        List<Student> list = studentMapper.findByIdsMap(map);
        System.out.println(list);
    }

    @Test
    public void findByIdAndName() {
        List<Student> list = studentMapper.findByIdAndName(1, "赵");
        System.out.println(list);
    }

    @Test
    public void getSisterByCode() {
        Student tly = studentMapper.getSisterByCode("tly");
        System.out.println(tly);
    }

    @Test
    public void update() {
        Student s = new Student();
        s.setId(1);
        s.setName("赵丽颖小姐姐");
        s.setStuNo("201801F");
        studentMapper.update(s);
    }

    @Test
    public void update1() {
        Student s = new Student();
        s.setId(1);
        s.setName("我的赵丽颖小姐姐");
        s.setStuNo("201801F");
        studentMapper.update1(s);
    }
}