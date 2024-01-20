package com.yangsx95.demo.mapper;

import com.yangsx95.demo.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * StudentMapper
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 11:15
 */
public interface StudentMapper {

    List<Student> findAll(@Param("name") String name);

    List<Student> findAllStu(@Param("name") String name);

    List<Student> findByIds(@Param("ids") Integer[] ids);

    /*mapper方法不可以重载，下面的写法是错误的*/
    //List<Student> findByIds(@Param("ids") List<Integer> ids);
    //正确的写法
    List<Student> findByIdsList(@Param("ids") List<Integer> ids);

    List<Student> findByIdsMap(@Param("ids") Map<String, Integer> ids);

    /*多个参数处理*/
    List<Student> findByIdAndName(@Param("id") Integer id, @Param("name") String name);

    /*使用chose进行查询条件筛选，chose类似switch，不同的时，当chose成功后，就立刻停止chose，如果没有符合条件的会进入otherwise中*/
    /*根据小姐姐的编号，来翻牌小姐姐*/
    Student getSisterByCode(@Param("code") String code);

    /*更新操作*/
    void update(@Param("student") Student student);

    /*使用trim标签进行update操作*/
    void update1(@Param("student") Student student);
}
