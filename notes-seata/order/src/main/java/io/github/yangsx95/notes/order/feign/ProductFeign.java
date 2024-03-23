package io.github.yangsx95.notes.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@FeignClient(name = "product", path = "/product")
public interface ProductFeign {

    @GetMapping(value = "/deductionInventory")
    void deductionInventory(
            @RequestParam("productNo") String productNo,
            @RequestParam("count") Integer count);

}
