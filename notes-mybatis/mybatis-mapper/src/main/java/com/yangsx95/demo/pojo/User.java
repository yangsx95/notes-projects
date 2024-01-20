package com.yangsx95.demo.pojo;

import lombok.Data;
import com.yangsx95.demo.enums.Gender;

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
