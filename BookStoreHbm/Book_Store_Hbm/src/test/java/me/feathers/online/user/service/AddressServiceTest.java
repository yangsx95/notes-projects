package me.feathers.online.user.service;

import org.testng.annotations.Test;

import me.feathers.online.entity.Address;
import me.feathers.online.util.BeansFactory;

/**
 * 地址服务层实现类
 *
 * @author Feathers
 * @create 2017-07-01-10:19
 */
public class AddressServiceTest {

    private AddressService addressService = (AddressService) BeansFactory.getBean("addressService");

    @Test
    public void testSetDefault() {
        Address address = addressService.findById(2L);
        addressService.setDefault(address);
    }

}
