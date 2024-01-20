package io.github.yangsx95.notes.mybatis.enumhandler.mapper;

import io.github.yangsx95.notes.mybatis.enumhandler.enums.ComputerState;
import io.github.yangsx95.notes.mybatis.enumhandler.pojo.Computer;
import io.github.yangsx95.notes.mybaitis.common.FlywayH2MybatisTools;
import org.junit.Test;

/**
 * ComputerMapperTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 23:01
 */
public class ComputerMapperTest {

    private static final ComputerMapper computerMapper
            = FlywayH2MybatisTools.getMapper("SqlMapConfig-CustomTypeHandler.xml", ComputerMapper.class);

    @Test
    public void save() {
        Computer computer = new Computer();
        computer.setName("联想");
        computer.setStatus(ComputerState.CLOSE);
        computerMapper.save(computer);
    }

    @Test
    public void findById() {
        Computer computer = computerMapper.findById(1);
        System.out.println(computer);
    }

}