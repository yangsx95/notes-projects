package me.feathers.online.user.service;

import java.util.List;

import me.feathers.online.entity.Address;

/**
 * @author Feathers
 * @create 2017-07-01-10:10
 */
public interface AddressService {

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

    // 设置默认地址
    void setDefault(Address address);
}
