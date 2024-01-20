package com.yangsx95.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author 杨顺翔
 * @since 2022/07/10
 */
@Data
// 指定表名，如果不填，则是当前schema的当前实体类名的表
@TableName(value = "`USER`", schema = "MYBATIS")
public class UserEntity {

    // 主键列名，如果不写，默认找id字段，如果加入此注解，则会以当前字段作为主键
    // 如果列名称与字段名称不同，那需要VALUE字段指定数据库主键列名
    // type属性适用于指定主键Id的生成策略：
    //    NONE，不设置，跟随全局配置
    //    AUTO，数据库ID自增，程序不指定，然后交由数据库生成，mysql就需要给主键增加auto
    //    ASSIGN_ID，全局的雪花算法策略
    //    ASSIGN_UUID，通过UUID生成
    // 如果已经给ID赋值，则不会使用任何ID生成策略生成新的ID
    
    // ####### 使用Sequence作为主键生成策略
    // 1. 定义IKeyGenerator实现，然后注入到Spring容器，参见MybatisPlusConfig
    // 2. 使用@KeySequence("seq名称")注解指定主键生成策略
    // 3. @KeySequence需要加到类上
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    // 当字段名称与数据库列表不一致，或者无法通过驼峰命名转换，就需要该注解进行映射
    @TableField(value = "username")
    private String username;

    private String password;

    // 新增更新时字段的策略
    // DEFAULT 默认策略，跟随全局（全局默认为not null）
    // IGNORED 不做任何判断，直接写入，如果要写入null采用此方式
    // NOT_NULL 如果是null，则不写入
    // NOT_EMPTY 如果是empty，则不写入
    // NEVER 不加入SQL，也就是始终不更新
    @TableField(insertStrategy = FieldStrategy.DEFAULT, updateStrategy = FieldStrategy.IGNORED)
    private Integer gender;

    // 逻辑删除，查询时会自动拼接delStatus=0，删除时会将该字段置为0
    @TableLogic
    private Integer deleted;
    
    private Integer status;

    // fill 插入/更新时，做属性填充
    // 参见MetaObjectHandler
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
