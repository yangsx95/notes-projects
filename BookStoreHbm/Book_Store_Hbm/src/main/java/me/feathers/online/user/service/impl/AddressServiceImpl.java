package me.feathers.online.user.service.impl;

import java.util.List;

import me.feathers.online.entity.Address;
import me.feathers.online.user.dao.AddressDao;
import me.feathers.online.user.service.AddressService;
import me.feathers.online.util.BeansFactory;
import me.feathers.online.util.HibernateTemplate;

/**
 * 地址服务层实现类
 *
 * @author Feathers
 * @create 2017-07-01-10:11
 */
public class AddressServiceImpl implements AddressService {

    private AddressDao dao = (AddressDao) BeansFactory.getBean("addressDao");

    @Override
    public void save(Address address) {
        dao.save(address);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Address findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public void update(Address address) {
        dao.update(address);
    }

    @Override
    public void setDefault(Address address) {
        List<Address> list = this.findByUserId(address.getUser().getUserId());
        for (Address a : list) {
            a.setIsDefault("0");
        }
        address.setIsDefault("1");
    }
}
