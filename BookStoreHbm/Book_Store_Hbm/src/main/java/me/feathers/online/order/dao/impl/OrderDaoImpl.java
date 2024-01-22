package me.feathers.online.order.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderStatus;
import me.feathers.online.entity.User;
import me.feathers.online.order.dao.OrderDao;
import me.feathers.online.util.HibernateTemplate;

/**
 * 订单持久层
 *
 * @author Feathers
 * @create 2017-07-02-16:43
 */
@SuppressWarnings("all")
public class OrderDaoImpl implements OrderDao {
    @Override
    public void save(Order o) {
        HibernateTemplate.execute(ses -> {
            ses.save(o);
            return null;
        });
    }

    @Override
    public Order findById(Long id) {
        return (Order) HibernateTemplate.execute(ses -> {
            return ses.get(Order.class, id);
        });
    }

    @Override
    public void delete(Order o) {
        HibernateTemplate.execute(ses -> {
            ses.delete(o);
            return null;
        });
    }

    @Override
    public List<Order> findByPage(User user, Integer pageNow, Integer pageSize,
                                  OrderStatus status, String orderBy, String orderNum,
                                  Date start, Date end) {
        return (List<Order>) HibernateTemplate.execute(ses -> {

            Criteria c = ses.createCriteria(Order.class, "o");
            c.setProjection(Projections.distinct(Projections.property("orderId")));

            if (status != null) {
                c.add(Restrictions.eq("o.orderStatus", status));
            }
            if (orderNum != null) {
                c.add(Restrictions.like("o.orderNum", "%"+orderNum+"%"));
            }
            if (pageNow != null && pageSize != null) {
                c.setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize);
            }
            Date sstart = null;
            if (start == null) {
                sstart = new Date(0);
            } else {
                sstart = start;
            }

            Date send = null;
            if (end == null) {
                send = new Date();
            } else {
                send = end;
            }
            c.add(Restrictions.between("o.createDate", sstart, send));

            List<Long> ids = c.list();
//            System.out.println("~~~~~~~~~~~~~~list~~~~~~~~~~~~~" + ids);
            List<Order> list = new ArrayList<>();
            for (int i = 0; i < ids.size(); i++) {
                Order order = (Order) ses.get(Order.class, ids.get(i));
                list.add(order);
            }

//            System.out.println("-----排序之前" + list);

            if (orderBy != null) {
//                System.out.println("------------开始了排序" + orderBy);
                if (orderBy.equals("asc")) {
                    Collections.sort(list, new Comparator<Order>() {
                        @Override
                        public int compare(Order o1, Order o2) {
                            if (o1.getCreateDate().after(o2.getCreateDate())){
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    });
                } else {
                    Collections.sort(list, new Comparator<Order>() {
                        @Override
                        public int compare(Order o1, Order o2) {
                            if (o1.getCreateDate().before(o2.getCreateDate())){
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    });
                }
            }
//            System.out.println("-----排序之后" + list);
            return list;
        });
    }

    @Override
    public long rowCount(User user,OrderStatus status) {
        return (long) HibernateTemplate.execute(ses -> {
            Criteria c = ses.createCriteria(Order.class, "o");
            if (user != null) {
                c.add(Restrictions.eq("o.user", user));
            }
            if (status != null){
                c.add(Restrictions.eq("o.orderStatus", status));
            }
            return c.setProjection(Projections.rowCount()).uniqueResult();
        });
    }
}
