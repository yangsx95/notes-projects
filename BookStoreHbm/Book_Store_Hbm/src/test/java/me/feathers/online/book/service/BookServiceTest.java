package me.feathers.online.book.service;

import org.testng.annotations.Test;

import me.feathers.online.entity.Book;
import me.feathers.online.entity.Category;
import me.feathers.online.entity.PageBean;
import me.feathers.online.util.BeansFactory;

/**
 * @author Feathers
 * @create 2017-06-27-15:57
 */
public class BookServiceTest {

    private BookService bookService = (BookService) BeansFactory.getBean("bookService");

    @Test
    public void testFindByPage() {
        PageBean<Book> bean = bookService.findByPage("洞", null, 1, 5);
        System.out.println("当前类--BookServiceTest,pageBean" + bean);

//        PageBean<Book> bean1 = bookService.findByPage(null, Category.精选图书, 1, 5);
//        System.out.println("当前类--BookServiceTest,pageBean" + bean1.getDatas());
    }
}

