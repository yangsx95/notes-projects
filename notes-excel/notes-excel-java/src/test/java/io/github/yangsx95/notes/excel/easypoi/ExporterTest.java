package io.github.yangsx95.notes.excel.easypoi;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 杨顺翔
 * @since 2022/08/12
 */
public class ExporterTest {

    @Test
    public void export2File() {
        ExcelPageExporter.Builder
                .builder(Student.class)
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
                .sheetName("学生列表")
                .title("学生列表标题")
                .build()
                .export2File("/Users/yangsx/temp/ttt.xlsx");
        
    }
}
