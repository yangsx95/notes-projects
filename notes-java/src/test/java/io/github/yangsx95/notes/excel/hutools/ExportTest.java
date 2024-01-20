package io.github.yangsx95.notes.excel.hutools;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 杨顺翔
 * @since 2022/08/13
 */
public class ExportTest {

    @Test
    public void export2File() {
        ExcelPageExport.Builder
                .builder(Student.class)
                .pageSize(500)
                .header("stuName", "学生名称")
                .header("className", "学生班级")
                .header("stuDate", "入学时间")
                .header("money", "学费")
                .dataSupplier((currentPage, pageSize) -> {
                    if (currentPage == 10000) {
                        return Collections.emptyList();
                    }
                    List<Student> list = new ArrayList<>();
                    Student student = new Student();
                    student.setMoney(new BigDecimal("100.43563"));
                    student.setStuDate(new Date());
                    student.setStuName("张三");
                    student.setClassName("三年级一班");
                    list.add(student);
                    return list;
                })
                .build()
                .export2File("/Users/yangsx/temp/ttt.xlsx");
    }
    
}
