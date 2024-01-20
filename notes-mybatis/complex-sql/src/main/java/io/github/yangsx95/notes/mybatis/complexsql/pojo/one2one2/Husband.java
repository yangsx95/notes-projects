package io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one2;

import lombok.Data;

import java.util.Date;

/**
 * Husband
 * <p>
 *
 * @author Feathers
 * @date 2018-05-26 22:10
 */
@Data
public class Husband {
    private Long husId;
    private String husName;
    private int age;
    private Date birthday;
}