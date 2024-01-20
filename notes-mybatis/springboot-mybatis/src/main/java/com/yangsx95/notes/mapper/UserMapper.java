package com.yangsx95.notes.mapper;

import com.yangsx95.notes.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/8/26
 */
@Mapper
public interface UserMapper {

    User findById(Integer id);

    Long save(User user);

    void update(User user);

    List<User> findAll(String name);

    void delById(Integer id);

    @Select("select * from MYBATIS.\"USER\"")
    List<User> findAllWithoutCondition();
}
