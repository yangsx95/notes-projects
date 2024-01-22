package io.github.yangsx95.notes.dubbo.productapi.service;

import io.github.yangsx95.notes.dubbo.productapi.dto.QueryProductByCodeResp;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/3/21
 */
public interface ProductService {

    /**
     * 更改库存
     * @param productCode 产品id
     * @param num 扣除数量
     */
    void minusStock(String productCode, Integer num);

    QueryProductByCodeResp queryProductByCode();
}
