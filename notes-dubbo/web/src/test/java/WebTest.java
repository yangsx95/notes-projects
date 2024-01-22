import io.github.yangsx95.notes.dubbo.orderapi.dto.QueryOrderByIdReq;
import io.github.yangsx95.notes.dubbo.orderapi.dto.QueryOrderByIdResp;
import io.github.yangsx95.notes.dubbo.orderapi.service.OrderService;
import io.github.yangsx95.notes.dubbo.productapi.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/3/21
 */
public class WebTest {
    ApplicationContext context;
    @Before
    public void loadSpringApplicationContext() {
         context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
    }
    
    @Test
    public void testSpringBeans() {
        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test
    public void testOrderService() {
        OrderService productService = (OrderService) context.getBean("orderService");
        QueryOrderByIdReq queryOrderByIdReq = new QueryOrderByIdReq();
        queryOrderByIdReq.setOrderId(1L);
        QueryOrderByIdResp queryOrderByIdResp = productService.queryOrderById(queryOrderByIdReq);
        System.out.println(queryOrderByIdResp.getProductName());
    }

    @Test
    public void testProductService() {
        ProductService productService = (ProductService) context.getBean("productService");
        QueryOrderByIdReq req = new QueryOrderByIdReq();
        req.setOrderId(1);
        System.out.println(productService.queryProductByCode().getMoney());
    }
}
