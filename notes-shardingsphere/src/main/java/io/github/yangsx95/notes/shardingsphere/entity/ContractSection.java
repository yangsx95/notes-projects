package io.github.yangsx95.notes.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author spikeCong
 * @date 2022/11/11
 **/
@TableName("t_contract_section")
@Data
@ToString
public class ContractSection {

    //通过MyBatisPlus生成主键
//    @TableId(value="cid",type = IdType.ASSIGN_ID)
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long cid;

    private Long userId;

    private Long corderNo;

    private String sectionName;

    private int status;

}
