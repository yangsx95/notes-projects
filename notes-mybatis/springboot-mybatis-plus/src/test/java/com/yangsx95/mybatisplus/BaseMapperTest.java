package com.yangsx95.mybatisplus;

import com.yangsx95.mybatisplus.mapper.UserMapper;
import com.yangsx95.mybatisplus.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 杨顺翔
 * @since 2022/07/10
 */
@SpringBootTest
class BaseMapperTest {

    @Resource
    private UserMapper userMapper;
    
    @Test
    void testSelectList() {
        List<UserEntity> userEntities = userMapper.selectList(null);
        System.out.println(userEntities);
    }

    @Test
    void testInsert() {
        UserEntity entity = new UserEntity();
        entity.setUsername("张三");
        entity.setPassword("123456");
        entity.setGender(1);
        entity.setDeleted(0);
        // 插入id默认使用雪花算法
        int num = userMapper.insert(entity); 
        Assertions.assertEquals(num, 1);
    }
    
    @Test
    void testDeleteBatch() {
        int num = userMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(num); // 如果某个id的数据不存在，则不会删除
    }
    
}
