package com.yangsx95.notes.spring.validation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidatorTests {

    @Builder
    @Getter
    @Setter
    static class Person {

        private String name;

        private Integer age;
    }

    static class PersonValidator implements Validator {

        @Override
        public boolean supports(Class<?> aClass) {
            return Person.class.equals(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "person.name.required");
            Person p = (Person) o;
            if (p.getAge() == null) {
                errors.rejectValue("age", "person.age.required");
            } else if (p.getAge() < 0) {
                errors.rejectValue("age", "person.age.illegal");
            } else if (p.getAge() > 110) {
                errors.rejectValue("age", "person.age.older");
            }
        }
    }

    @Test
    public void testPersonValidator() {
        PersonValidator personValidator = new PersonValidator();
//        personValidator.validate(new Person.PersonBuilder().age(-10).name("李四").build(), Errors);
    }


}
