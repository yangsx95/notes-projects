package io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one2;

import lombok.Data;

import java.util.Date;

/**
 * Wife
 * <p>
 *
 * @author Feathers
 * @date 2018-05-26 22:13
 */
@Data
public class Wife {
    private Long wifeId;
    private String wifeName;
    private int age;
    private Date birthday;
    // 在wife中维护一个husband对象
    private Husband husband;
}
