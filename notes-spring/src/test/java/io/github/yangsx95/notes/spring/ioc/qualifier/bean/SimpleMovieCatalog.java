package io.github.yangsx95.notes.spring.ioc.qualifier.bean;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

public class SimpleMovieCatalog {


    public List<String> listMovie() {
        return Arrays.asList("复仇者联盟", "唐老鸭", "咒怨");
    }
}
