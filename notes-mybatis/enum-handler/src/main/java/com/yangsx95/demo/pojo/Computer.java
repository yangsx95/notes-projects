package com.yangsx95.demo.pojo;

import lombok.Data;
import com.yangsx95.demo.enums.ComputerState;

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
