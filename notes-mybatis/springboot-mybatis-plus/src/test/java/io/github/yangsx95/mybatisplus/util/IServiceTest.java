package io.github.yangsx95.mybatisplus.util;

import com.yangsx95.mybatisplus.entity.UserEntity;
import com.yangsx95.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 杨顺翔
 * @since 2022/07/10
 */
@SpringBootTest
 class IServiceTest {

    @Resource
    private UserService userService;

    /**
     * 批量插入，内部实际上是一个for循环单条单条插入
     */
    @Test
    void saveBatch() {
        Collection<UserEntity> userList = new ArrayList<>(3);
        for (int i = 1; i <= 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setUsername("黑客" + i + "号");
            entity.setPassword("123456");
            entity.setGender(1);
            entity.setStatus(1);
            userList.add(entity);
        }
        userService.saveBatch(userList);
    }
    
    /**
     * 根据实体对象是否有主键决定是进行增加还是修改操作
     */
    @Test
    void saveOrUpdate() {
        UserEntity entity = new UserEntity();
        entity.setUsername("李四");
        entity.setPassword("1234567");
        entity.setGender(0);
        entity.setStatus(1);
        userService.saveOrUpdate(entity);

        List<UserEntity> list = userService.list();
        System.out.println(list);
    }

    /**
     * 主键相同更新，没有主键的将会新增
     * （不会删除元素，因为没有查询条件）
     */
    @Test
    void saveOrUpdateBatch() {
        List<UserEntity> list = userService.list();
        UserEntity entity = new UserEntity();
        entity.setUsername("王八");
        entity.setPassword("1234");
        entity.setGender(0);
        entity.setStatus(1);
        // 新增一个元素
        list.add(entity);
        // 修改一个元素
        list.get(0).setUsername("我是修改的");

        userService.saveOrUpdateBatch(list);

        List<UserEntity> newList = userService.list();
        System.out.println(newList);
    }
}
