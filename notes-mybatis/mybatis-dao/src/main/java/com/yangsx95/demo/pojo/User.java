package com.yangsx95.demo.pojo;

import lombok.Data;
import com.yangsx95.demo.enums.Gender;

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
