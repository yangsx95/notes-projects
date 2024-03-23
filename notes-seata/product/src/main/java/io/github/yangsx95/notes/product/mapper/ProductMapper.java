package io.github.yangsx95.notes.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.yangsx95.notes.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
