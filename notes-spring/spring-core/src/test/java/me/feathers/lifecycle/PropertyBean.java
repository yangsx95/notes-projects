package me.feathers.lifecycle;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/4/1
 */
public class PropertyBean {

    private String name;

    private String password;

    public PropertyBean() {
        System.out.println("property bean 创建");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PropertyBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
