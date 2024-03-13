package io.github.yangsx95.notes.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yangsx95.notes.shardingsphere.entity.Contract;
import io.github.yangsx95.notes.shardingsphere.entity.Course;
import io.github.yangsx95.notes.shardingsphere.entity.CourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author spikeCong
 * @date 2022/11/11
 **/
@Mapper
public interface ContractMapper extends BaseMapper<Contract> {

}
