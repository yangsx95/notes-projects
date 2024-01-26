package io.github.yangsx95.notes.kafka.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private Integer id;

    private String name;

    private Date birthday;

}
