package me.feathers.online.user.dao.impl;

import org.hibernate.criterion.Restrictions;

import java.util.List;

import me.feathers.online.entity.Address;
import me.feathers.online.user.dao.AddressDao;
import me.feathers.online.util.HibernateTemplate;

/**
 * 地址dao实现类
 *
 * @author Feathers
 * @create 2017-07-01-9:33
 */
@SuppressWarnings("all")
public class AddressDaoImpl implements AddressDao {
    @Override
    public void save(Address address) {
        // 如果是默认地址的
        // 先将数据库中已有的地址全部设置为“0” 再添加一个默认地址

        HibernateTemplate.execute(ses -> {
            if (address.getIsDefault().equals("1")) {
                List<Address> addresses = findByUserId(address.getUser().getUserId());
                for (Address addr : addresses) {
                    addr.setIsDefault("0");
                    ses.update(addr);
                }
            }
            ses.save(address);
            return null;
        });
    }

    @Override
    public void deleteById(Long id) {
        HibernateTemplate.execute(ses -> {
            Address ad = this.findById(id);
            if (ad != null) {
                ses.delete(ad);
            }
            return null;
        });

    }

    @Override
    public Address findById(Long id) {
        return (Address) HibernateTemplate.execute(ses -> {
            return ses.get(Address.class, id);
        });
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return (List<Address>) HibernateTemplate.execute(ses -> {
            return ses.createCriteria(Address.class, "ad")
                    .add(Restrictions.eq("user.userId", userId))
                    .list();
        });
    }

    @Override
    public void update(Address address) {
        HibernateTemplate.execute(ses -> {
            if (null != this.findById(address.getAddressId())) {
                ses.update(address);
            }
            return null;
        });
    }

}
