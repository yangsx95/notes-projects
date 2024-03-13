package io.github.yangsx95.notes.shardingsphere;

import io.github.yangsx95.notes.shardingsphere.entity.Contract;
import io.github.yangsx95.notes.shardingsphere.entity.Course;
import io.github.yangsx95.notes.shardingsphere.mapper.ContractMapper;
import io.github.yangsx95.notes.shardingsphere.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * 水平分表测试
 *
 * @author yangshunxiang
 * @since 2024/3/12
 */
@SpringBootTest
@ActiveProfiles("水平分库加分表")
public class 水平分库加分表Test {

    @Resource
    private ContractMapper contractMapper;

    @Test
    public void insert() {

        for (int i = 100; i < 150; i++) {
            Contract contract = new Contract();
            contract.setUserId(1L + i);
            contract.setCname("Java面试题详解");
            contract.setCorderNo(1000L + i);
            contract.setBrief("经典的10000道面试题");
            contract.setPrice(100.00);
            contract.setStatus(1);

            contractMapper.insert(contract);
        }
    }

    @Test
    public void select() {
        contractMapper.selectList(null).forEach(System.out::println);
    }

    /**
     * 使用分片键字段查询
     */
    @Test
    public void selectByKey() {
        // 2是偶数，应该从 t_course_1 表中查询
        contractMapper.selectById(2);
    }
}
