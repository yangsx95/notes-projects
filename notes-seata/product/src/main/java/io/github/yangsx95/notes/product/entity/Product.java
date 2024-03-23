package io.github.yangsx95.notes.product.entity;

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
@TableName("t_product")
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("product_no")
    private String productNo;

    /**
     * 剩余库存
     */
    @TableField("inventory")
    private Integer inventory;
}
