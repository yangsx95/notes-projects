package io.github.yangsx95.notes.shardingsphere;

import io.github.yangsx95.notes.shardingsphere.entity.Course;
import io.github.yangsx95.notes.shardingsphere.entity.PayOrder;
import io.github.yangsx95.notes.shardingsphere.entity.User;
import io.github.yangsx95.notes.shardingsphere.mapper.CourseMapper;
import io.github.yangsx95.notes.shardingsphere.mapper.PayOrderMapper;
import io.github.yangsx95.notes.shardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.List;

/**
 * 水平分表测试
 *
 * @author yangshunxiang
 * @since 2024/3/12
 */
@SpringBootTest
@ActiveProfiles("水平分表")
public class 水平分表Test {

    @Resource
    private CourseMapper courseMapper;

    @Test
    public void insert() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setUserId(1L + i);
            course.setCname("Java面试题详解");
            course.setBrief("经典的10000道面试题");
            course.setPrice(100.00);
            course.setStatus(1);

            courseMapper.insert(course);
        }
    }

    @Test
    public void select() {
        courseMapper.selectList(null).forEach(System.out::println);
    }

    /**
     * 使用分片键字段查询
     */
    @Test
    public void selectByKey() {
        // 2是偶数，应该从 t_course_1 表中查询
        courseMapper.selectById(2);
    }
}
