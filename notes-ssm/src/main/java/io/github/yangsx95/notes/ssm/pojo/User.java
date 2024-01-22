package io.github.yangsx95.notes.ssm.pojo;

import io.github.yangsx95.notes.ssm.enums.Gender;
import lombok.Data;

/**
 * User
 * <p>
 *
 * @author Feathers
 * @date 2018-05-23 15:57
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private Gender gender;
    private Integer delStatus;
}
