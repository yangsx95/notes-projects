package io.github.yangsx95.notes.json.fastjson.valuefilter;

public class Person {

    private String id;

    private String name;
    
    @Desensitization(type = Desensitization.DesensitizationType.PHONE)
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
