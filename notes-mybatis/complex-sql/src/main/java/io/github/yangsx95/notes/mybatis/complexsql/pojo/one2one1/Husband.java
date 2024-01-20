package io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one1;

import lombok.Data;

import java.util.Date;

/**
 * Husband类
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 18:05
 */
@Data
public class Husband {
    private Long hus_id;
    private String hus_name;
    private int age;
    private Date birthday;
}
