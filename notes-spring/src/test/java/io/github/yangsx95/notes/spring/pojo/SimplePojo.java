package io.github.yangsx95.notes.spring.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SimplePojo {

    private String name;

    private Integer age;

    private List<String> hobby;

}
