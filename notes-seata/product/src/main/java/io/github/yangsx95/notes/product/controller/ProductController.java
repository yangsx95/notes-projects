package io.github.yangsx95.notes.product.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.yangsx95.notes.product.entity.Product;
import io.github.yangsx95.notes.product.mapper.ProductMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    /**
     * 扣减库存
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping(value = "/deductionInventory")
    public void deductionInventory(
            @RequestParam("productNo") String productNo,
            @RequestParam("count") Integer count) {
        // 查询商品信息
        List<Product> products = productMapper.selectList(Wrappers.lambdaQuery(Product.class).eq(Product::getProductNo, productNo));
        // 如果没查到，插入一条商品信息（这是为了方便测试用的）
        if (products.isEmpty()) {
            productMapper.insert(new Product(null, productNo, 10));
            products = productMapper.selectList(Wrappers.lambdaQuery(Product.class).eq(Product::getProductNo, productNo));
        }
        Product product = products.get(0);

        // 如果商品库存不够了，抛出异常
        if (product.getInventory() < count) {
            throw new RuntimeException("库存不够了");
        }

        // 库存够了，进行扣减库存操作
        product.setInventory(product.getInventory() - count);
        productMapper.updateById(product);
    }

}
