package com.yangsx95.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangsx95.mybatisplus.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper注解是可选的，也可以使用Repository，也可以不写
 *
 * @author 杨顺翔
 * @since 2022/07/10
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
