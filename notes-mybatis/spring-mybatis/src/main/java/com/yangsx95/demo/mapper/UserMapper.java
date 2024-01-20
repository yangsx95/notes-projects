package com.yangsx95.demo.mapper;

import com.yangsx95.demo.pojo.User;

import java.util.List;

/**
 * UserMapper
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 10:16
 */
public interface UserMapper {

    /*当参数名称和配置文件不一致时，可以使用@Param注解指定名称*/
    User findById(Integer id);

    Long save(User user);

    void update(User user);

    List<User> findAll(String name);

    void delById(Integer id);
}
