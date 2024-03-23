package io.github.yangsx95.notes.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@FeignClient(name = "pay", path = "pay")
public interface PayFeign {

    @GetMapping(value = "/pay")
    void payMoney(
            @RequestParam("accountNo") String accountNo,
            @RequestParam("amount") BigDecimal amount
    );

}
