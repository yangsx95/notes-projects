package io.github.yangsx95.notes.mybatis.dao.pojo;

import lombok.Data;
import io.github.yangsx95.notes.mybatis.dao.enums.Gender;

/**
 * User
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 9:09
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    private Gender gender;
    private Integer delStatus;
}
