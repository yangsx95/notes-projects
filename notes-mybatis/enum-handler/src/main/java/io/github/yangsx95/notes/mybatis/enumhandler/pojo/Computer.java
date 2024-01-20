package io.github.yangsx95.notes.mybatis.enumhandler.pojo;

import io.github.yangsx95.notes.mybatis.enumhandler.enums.ComputerState;
import lombok.Data;

/**
 * Computer
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 15:21
 */
@Data
public class Computer {
    private Integer id;
    private String name;
    private ComputerState status;
}
