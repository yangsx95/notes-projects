package com.yangsx95.demo.mapper;

import com.yangsx95.demo.pojo.Computer;

/**
 * ComputerMapper
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 22:26
 */
public interface ComputerMapper {

    Computer findById(Integer id);

    void save(Computer computer);
}
