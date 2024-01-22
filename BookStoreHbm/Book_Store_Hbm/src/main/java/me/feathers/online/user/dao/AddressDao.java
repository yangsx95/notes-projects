package me.feathers.online.user.dao;

import java.util.List;

import me.feathers.online.entity.Address;
import me.feathers.online.entity.User;

/**
 * 地址持久层
 *
 * @author Feathers
 * @create 2017-07-01-9:29
 */
public interface AddressDao {

    // 添加一个新的地址
    void save(Address address);

    // 删除一个指定的地址
    void deleteById(Long id);

    // 根据id查找地址
    Address findById(Long id);

    // 根据用户id查找地址
    List<Address> findByUserId(Long userId);

    // 更新address
    void update(Address address);
}
