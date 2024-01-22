package io.github.yangsx95.notes.dubbo.web.controller;

import io.github.yangsx95.notes.dubbo.orderapi.dto.AddOrderReq;
import io.github.yangsx95.notes.dubbo.orderapi.service.OrderService;
import io.github.yangsx95.notes.dubbo.productapi.dto.QueryProductByCodeResp;
import io.github.yangsx95.notes.dubbo.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/3/20
 */
@Controller
public class IndexController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

   
    
    /**
     * 购买商品
     *
     * @param username    用户名
     * @param productCode 商品代码
     * @param num         商品数量
     */
    @GetMapping("/buyProduct")
    @ResponseBody
    public String buyProduct(String username, String productCode, Integer num) {
        QueryProductByCodeResp product = productService.queryProductByCode();

        AddOrderReq req = new AddOrderReq();
        req.setUsername(username);
        req.setProductCode(productCode);
        req.setProductName(product.getProductName());
        req.setMoney(product.getMoney());
        req.setNum(num);
        orderService.addOrder(req);
        return "succes" + product.getProductName();
    }
}
