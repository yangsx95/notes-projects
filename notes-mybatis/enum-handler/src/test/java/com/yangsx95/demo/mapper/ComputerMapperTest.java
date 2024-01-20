package com.yangsx95.demo.mapper;

import com.yangsx95.demo.enums.ComputerState;
import com.yangsx95.demo.pojo.Computer;
import com.yangsx95.mybaitis.common.FlywayH2MybatisTools;
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