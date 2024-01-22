package me.feathers.online.book.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import me.feathers.online.book.dao.BookDao;
import me.feathers.online.entity.Book;
import me.feathers.online.entity.Category;
import me.feathers.online.util.HibernateTemplate;

/**
 * 图书持久层实现
 *
 * @author Feathers
 * @create 2017-06-27-13:17
 */
@SuppressWarnings("all")
public class BookDaoImpl implements BookDao {

    public Book findById(final Long id) {
        return (Book) HibernateTemplate.execute(ses -> {
            return ses.get(Book.class, id);
        });
    }

    public long getRowCount(Category cate, String bookName) {
        return (long) HibernateTemplate.execute(ses -> {
            Criteria c = ses.createCriteria(Book.class, "b");
            // 当传入的styleId处于已有的styleid的范围时，则添加styleid搜索条件
            if (cate != null) {
                c.add(Restrictions.eq("b.category", cate));
            }
            if (bookName != null && bookName.trim().length() > 0) {
                c.add(Restrictions.like("b.name", "%"+bookName+"%"));
            }
            return c.setProjection(Projections.rowCount()).uniqueResult();
        });
    }

    public List<Book> findByPage(String bookName, Category category, Integer pageNow, Integer pageSize) {
        return (List<Book>) HibernateTemplate.execute(ses -> {
            Criteria c = ses.createCriteria(Book.class, "b");
            if (bookName != null && bookName.trim().length() > 0) {
                c.add(Restrictions.like("b.name", "%" + bookName + "%"));
            }
            if (category != null) {
                c.add(Restrictions.eq("b.category", category));
            }
            if (pageNow != null && pageSize != null) {
                c.setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize);
            }
            return c.list();
        });
    }
}
