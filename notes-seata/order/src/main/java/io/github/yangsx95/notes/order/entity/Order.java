package io.github.yangsx95.notes.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("order_no")
    private String orderNo;

    @TableField("product_no")
    private String productNo;

    @TableField("account_no")
    private String accountNo;

    @TableField("count")
    private Integer count;

    @TableField("amount")
    private BigDecimal amount;
}
