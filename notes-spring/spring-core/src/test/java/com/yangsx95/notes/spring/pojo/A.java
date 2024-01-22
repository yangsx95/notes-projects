package com.yangsx95.notes.spring.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class A {

    private B b;

    public A(B b) {
        System.out.println("==== A construct, depends on B:" + b + " ====");
        this.b = b;
    }

}
