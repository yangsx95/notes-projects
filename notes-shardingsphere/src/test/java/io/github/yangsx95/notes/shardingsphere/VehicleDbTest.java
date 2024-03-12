package io.github.yangsx95.notes.shardingsphere;

import io.github.yangsx95.notes.shardingsphere.entity.PayOrder;
import io.github.yangsx95.notes.shardingsphere.entity.User;
import io.github.yangsx95.notes.shardingsphere.mapper.PayOrderMapper;
import io.github.yangsx95.notes.shardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;

/**
 * 垂直分库测试
 * @author yangshunxiang
 * @since 2024/3/12
 */
@SpringBootTest
@ActiveProfiles("vehicledb")
public class VehicleDbTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PayOrderMapper payOrderMapper;

    @Test
    void insert() {
        User user = new User();
        user.setId(1003L);
        user.setUsername("猕猴桃");
        user.setPhone("123445555555");
        user.setPassword("123456");
        userMapper.insert(user);

        PayOrder payOrder = new PayOrder();
        payOrder.setOrder_id(2001L);
        payOrder.setProduct_name("电视");
        payOrder.setUser_id(user.getId());
        payOrderMapper.insert(payOrder);
    }

    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
