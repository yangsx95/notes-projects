package io.github.yangsx95.notes.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author spikeCong
 * @date 2022/11/8
 **/
@TableName("pay_order") //逻辑表名
@Data
@ToString
public class PayOrder {

    @TableId
    private long order_id;

    private long user_id;

    private String product_name;

    private int count;


}
