package io.github.yangsx95.notes.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yangsx95.notes.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
