package me.feathers.online.book.dao;

import org.testng.annotations.Test;

import java.util.List;

import me.feathers.online.entity.Book;
import me.feathers.online.entity.Category;
import me.feathers.online.util.BeansFactory;

/**
 * BookDao测试类
 *
 * @author Feathers
 * @create 2017-06-27-13:29
 */
public class BookDaoTest {

    private BookDao bookDao = (BookDao) BeansFactory.getBean("bookDao");

    @Test
    public void testFindById() {
        Book book = bookDao.findById(9L);
        System.out.println("当前类--BookDaoTest,testFindById---" + book);
    }

    @Test
    public void testGetRowCount() {
        long rowCount = bookDao.getRowCount(null, "洞");
        System.out.println("当前类--BookDaoTest,testGetRowCount----"+rowCount);
    }

    @Test
    public void testFindByPage() {
        List<Book> list = bookDao.findByPage("洞", null, null, null);
        list.forEach(System.out::println);
    }
}
