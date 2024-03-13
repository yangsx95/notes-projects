package io.github.yangsx95.notes.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yangsx95.notes.shardingsphere.entity.ContractSection;
import io.github.yangsx95.notes.shardingsphere.entity.ContractVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author spikeCong
 * @date 2022/11/11
 **/
@Mapper
public interface ContractSectionMapper extends BaseMapper<ContractSection> {


    @Select({"SELECT \n" +
            "  c.corder_no,\n" +
            "  c.cname,\n" +
            "  COUNT(cs.id) num\n" +
            "FROM t_contract c INNER JOIN t_contract_section cs ON c.corder_no = cs.corder_no\n" +
            "GROUP BY c.corder_no,c.cname"})
    List<ContractVo> getCourseNameAndSectionNum();
}
