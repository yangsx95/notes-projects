package me.feathers.demo.swagger2.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * User
 * <p>
 *
 * @author Feathers
 * @date 2018-05-31 15:53
 */
@ApiModel
public class User {
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty
    private String name;

    @ApiModelProperty(required = false, example = "20")
    private int age;

    public User() {
    }

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
