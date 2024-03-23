package io.github.yangsx95.notes.pay.entity;

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
@TableName("t_account")
public class Account {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("account_no")
    private String accountNo;

    /**
     * 账户余额
     */
    @TableField("account_balance")
    private BigDecimal accountBalance;
}
