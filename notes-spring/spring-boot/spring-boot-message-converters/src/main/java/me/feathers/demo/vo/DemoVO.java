package me.feathers.demo.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * DemoVO
 * <p>
 *
 * @author Feathers
 * @date 2018-04-01 19:41
 */
public class DemoVO {

    private Long id;
    private String name;
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

}
