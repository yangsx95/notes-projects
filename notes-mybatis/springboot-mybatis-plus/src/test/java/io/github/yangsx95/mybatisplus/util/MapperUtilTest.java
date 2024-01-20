package io.github.yangsx95.mybatisplus.util;

import com.yangsx95.mybatisplus.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author 杨顺翔
 * @since 2022/07/10
 */
@SpringBootTest
class MapperUtilTest {

    @Test
    @DisplayName("EntityClass对象为null")
    void groupListEntityClassIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> MapperUtil.groupData(null, null, null));
    }

    @Test
    @DisplayName("两个Null List分组")
    void groupListNullList() {
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(null, null, UserEntity.class);
        Assertions.assertAll(
                () -> assertTrue(groups.getNeedAdd().isEmpty()),
                () -> assertTrue(groups.getNeedUpdate().isEmpty()),
                () -> assertTrue(groups.getNotChange().isEmpty()),
                () -> assertTrue(groups.getNeedDelete().isEmpty()),
                () -> assertTrue(groups.getDirtyData().isEmpty())
        );
    }

    @Test
    @DisplayName("两个Empty List分组")
    void groupListEmptyList() {
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(Collections.emptyList(), Collections.emptyList(), UserEntity.class);
        Assertions.assertAll(
                () -> assertTrue(groups.getNeedAdd().isEmpty()),
                () -> assertTrue(groups.getNeedUpdate().isEmpty()),
                () -> assertTrue(groups.getNotChange().isEmpty()),
                () -> assertTrue(groups.getNeedDelete().isEmpty()),
                () -> assertTrue(groups.getDirtyData().isEmpty())
        );
    }

    @Test
    @DisplayName("原数据为空，只新增")
    void groupListOnlyAdd() {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setUsername("骑兵" + i + "号");
            entity.setPassword("123456");
            userEntityList.add(entity);
        }
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(Collections.emptyList(), userEntityList, UserEntity.class);
        Assertions.assertAll(
                () -> assertEquals(groups.getNeedAdd().size(), 3),
                () -> assertTrue(groups.getNeedUpdate().isEmpty()),
                () -> assertTrue(groups.getNotChange().isEmpty()),
                () -> assertTrue(groups.getNeedDelete().isEmpty()),
                () -> assertTrue(groups.getDirtyData().isEmpty())
        );
    }

    @Test
    @DisplayName("原数据为空，全是脏数据")
    void groupListOnlyDirty() {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setId(1000L + i);
            entity.setUsername("骑兵" + i + "号");
            entity.setPassword("123456");
            userEntityList.add(entity);
        }
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(Collections.emptyList(), userEntityList, UserEntity.class);
        Assertions.assertAll(
                () -> assertTrue(groups.getNeedAdd().isEmpty()),
                () -> assertTrue(groups.getNeedUpdate().isEmpty()),
                () -> assertTrue(groups.getNotChange().isEmpty()),
                () -> assertTrue(groups.getNeedDelete().isEmpty()),
                () -> assertEquals(groups.getDirtyData().size(), 3)
        );
    }

    @Test
    @DisplayName("删除原有的所有数据")
    void groupListDeleteAll() {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setId(1000L + i);
            entity.setUsername("骑兵" + i + "号");
            entity.setPassword("123456");
            userEntityList.add(entity);
        }
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(userEntityList, null, UserEntity.class);
        Assertions.assertAll(
                () -> assertTrue(groups.getNeedAdd().isEmpty()),
                () -> assertTrue(groups.getNeedUpdate().isEmpty()),
                () -> assertTrue(groups.getNotChange().isEmpty()),
                () -> assertEquals(groups.getNeedDelete().size(), 3),
                () -> assertTrue(groups.getDirtyData().isEmpty())
        );
    }

    @Test
    @DisplayName("没有数据需要更新")
    void groupListAllNotChange() {
        List<UserEntity> preList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setId(1000L + i);
            entity.setUsername("骑兵" + i + "号");
            entity.setPassword("123456");
            preList.add(entity);
        }
        List<UserEntity> curList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setId(1000L + i);
            entity.setUsername("骑兵" + i + "号");
            entity.setPassword("123456");
            curList.add(entity);
        }
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(preList, curList, UserEntity.class);
        Assertions.assertAll(
                () -> assertTrue(groups.getNeedAdd().isEmpty()),
                () -> assertTrue(groups.getNeedUpdate().isEmpty()),
                () -> assertEquals(groups.getNotChange().size(), 3),
                () -> assertTrue(groups.getNeedDelete().isEmpty()),
                () -> assertTrue(groups.getDirtyData().isEmpty())
        );
    }
    
    @Test
    @DisplayName("所有的数据都需要更新")
    void groupListAllNeedUpdate() {
        List<UserEntity> preList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setId(1000L + i);
            entity.setUsername("骑兵" + i + "号");
            entity.setPassword("123456");
            preList.add(entity);
        }
        List<UserEntity> curList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setId(1000L + i);
            entity.setUsername("骑兵" + i + "号黑");
            entity.setPassword("123456");
            curList.add(entity);
        }
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(preList, curList, UserEntity.class);
        Assertions.assertAll(
                () -> assertTrue(groups.getNeedAdd().isEmpty()),
                () -> assertEquals(groups.getNeedUpdate().size(), 3),
                () -> assertTrue(groups.getNotChange().isEmpty()),
                () -> assertTrue(groups.getNeedDelete().isEmpty()),
                () -> assertTrue(groups.getDirtyData().isEmpty())
        );
    }
    
    @Test
    @DisplayName("复杂的情况")
    void groupComplex() {
        List<UserEntity> preList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserEntity entity = new UserEntity();
            entity.setId(1000L + i);
            entity.setUsername("骑兵" + i + "号");
            entity.setPassword("123456");
            preList.add(entity);
        }
        List<UserEntity> curList = new ArrayList<>();
        // 新增1个
        UserEntity needAddEntity = new UserEntity();
        needAddEntity.setUsername("步兵x号");
        needAddEntity.setPassword("1234444");
        curList.add(needAddEntity);
        // 修改一个
        UserEntity needUpdateEntity = new UserEntity();
        needUpdateEntity.setId(1001L);
        needUpdateEntity.setUsername("骑兵1号");
        needUpdateEntity.setPassword("111111111");
        curList.add(needUpdateEntity);
        // 一个不动
        UserEntity noChange = new UserEntity();
        noChange.setId(1002L);
        noChange.setUsername("骑兵2号");
        noChange.setPassword("123456");
        curList.add(noChange);
        // 骑兵0号删除
        // 一个脏数据
        UserEntity dirtyData = new UserEntity();
        dirtyData.setId(19L);
        dirtyData.setUsername("我是脏数据");
        curList.add(dirtyData);
        
        MapperUtil.OprDataGroupList<UserEntity> groups = MapperUtil.groupData(preList, curList, UserEntity.class);
        Assertions.assertAll(
                () -> assertEquals(groups.getNeedAdd().size(), 1),
                () -> assertEquals(groups.getNeedUpdate().size(), 1),
                () -> assertEquals(groups.getNotChange().size(), 1),
                () -> assertEquals(groups.getNeedDelete().size(), 1),
                () -> assertEquals(groups.getDirtyData().size(), 1)
        );
        
    }
}