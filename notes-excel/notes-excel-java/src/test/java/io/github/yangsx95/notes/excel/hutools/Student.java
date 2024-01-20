package io.github.yangsx95.notes.excel.hutools;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 杨顺翔
 * @since 2022/08/12
 */
@Data
public class Student {

    private String stuName;

    private String className;

    private Date stuDate;

    private BigDecimal money;
    
}
