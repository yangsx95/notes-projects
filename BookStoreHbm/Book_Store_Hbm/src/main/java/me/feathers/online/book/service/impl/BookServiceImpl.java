package me.feathers.online.book.service.impl;

import java.util.List;

import me.feathers.online.book.dao.BookDao;
import me.feathers.online.book.service.BookService;
import me.feathers.online.entity.Book;
import me.feathers.online.entity.Category;
import me.feathers.online.entity.PageBean;
import me.feathers.online.util.BeansFactory;

/**
 * 图书业务层实现类
 *
 * @author Feathers
 * @create 2017-06-27-15:43
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = (BookDao) BeansFactory.getBean("bookDao");

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public long getRowCount(Category cate, String bookName) {
        return bookDao.getRowCount(cate, bookName);
    }

    @Override
    public PageBean<Book> findByPage(String bookName, Category category, Integer pageNow, Integer pageSize) {
        PageBean<Book> pageBean = new PageBean<>();
        Long count = bookDao.getRowCount(category, bookName);
        pageBean.setCount(count);
        // 如果pageSize伪null，设置一个默认为2的pageSize
        if(pageSize==null){
            pageSize = 2;
        }
        pageBean.setPageSize(pageSize);
        pageBean.setPageCount((int) ((count % pageSize == 0) ?
                (count / pageSize) : (count / pageSize + 1)));
        if (pageNow == null){
            pageNow = 1;
        }
        pageBean.setPageNow(pageNow);
        List<Book> books = bookDao.findByPage(bookName, category, pageNow, pageSize);
        pageBean.setDatas(books);
        return pageBean;
    }

}
