package io.github.yangsx95.notes.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yangsx95.notes.shardingsphere.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author spikeCong
 * @date 2022/11/8
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
