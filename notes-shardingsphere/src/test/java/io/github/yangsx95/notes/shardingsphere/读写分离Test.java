package io.github.yangsx95.notes.shardingsphere;

import io.github.yangsx95.notes.shardingsphere.entity.Contract;
import io.github.yangsx95.notes.shardingsphere.entity.ContractSection;
import io.github.yangsx95.notes.shardingsphere.entity.ContractVo;
import io.github.yangsx95.notes.shardingsphere.entity.PayOrder;
import io.github.yangsx95.notes.shardingsphere.mapper.ContractMapper;
import io.github.yangsx95.notes.shardingsphere.mapper.ContractSectionMapper;
import io.github.yangsx95.notes.shardingsphere.mapper.PayOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;

/**
 * 读写分离测试
 *
 * @author yangshunxiang
 * @since 2024/3/12
 */
@SpringBootTest
@ActiveProfiles("读写分离")
public class 读写分离Test {

    @Resource
    private PayOrderMapper payOrderMapper;

    @Test
    public void insert() {
        for (int i = 0; i < 3; i++) {
            PayOrder payOrder = new PayOrder();
            payOrder.setCount(10);
            payOrder.setUser_id(10);
            payOrder.setProduct_name("重中之重");
            payOrderMapper.insert(payOrder);
        }

    }

    //关联查询不会再产生笛卡尔积
    @Test
    public void testSelectCourseNameAndSectionNum() {
        payOrderMapper.selectList(null).forEach(System.out::println);
    }

}
