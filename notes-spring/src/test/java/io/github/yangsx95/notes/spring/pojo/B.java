package io.github.yangsx95.notes.spring.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class B {

    public B() {
        System.out.println("======= B construct ========");
    }
}
