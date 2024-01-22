package com.yangsx95.notes.anno.bean;

import com.yangsx95.notes.anno.dto.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    public void printPerson(Person person) {
        System.out.println(person);
    }

}
