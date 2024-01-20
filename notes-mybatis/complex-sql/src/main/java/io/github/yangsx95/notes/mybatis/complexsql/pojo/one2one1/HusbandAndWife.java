package io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * HusbandAndWife
 * 使用resultType，定义夫妻信息类，此po类中包括了丈夫信息和妻子信息
 *
 * 夫妻类继承husband类，包含丈夫和妻子的信息，这样就可以查询一对夫妻了
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 18:06
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class HusbandAndWife extends Husband {
    private String wifeName;
    private int age;
}
