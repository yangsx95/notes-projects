package io.github.yangsx95.notes.excel.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 杨顺翔
 * @since 2022/08/12
 */
@Data
public class Student {

    @Excel(name = "学生名称", orderNum = "1")
    private String stuName;

    @Excel(name = "学生班级", orderNum = "2")
    private String className;

    @Excel(name = "入学时间", orderNum = "3", format = "yyyy-MM-dd")
    private Date stuDate;

    @Excel(name = "学费", orderNum = "4", numFormat = ".##")
    private BigDecimal money;
    
}
