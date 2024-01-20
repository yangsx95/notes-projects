package io.github.yangsx95.notes.mybatis.enumhandler.mapper;

import io.github.yangsx95.notes.mybatis.enumhandler.pojo.Computer;

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
