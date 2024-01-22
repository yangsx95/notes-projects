package me.feathers.online.book.dao;

import java.util.List;

import me.feathers.online.entity.Book;
import me.feathers.online.entity.Category;

/**
 * Book操作数据持久层
 *
 * @author Feathers
 * @create 2017-06-27-11:33
 */
public interface BookDao {

    /**
     * 根据id寻找图书
     * @param id
     * @return
     */
    Book findById(Long id);

    /**
     * 统计分类图书的总数量
     * @param cate 分类
     * @return
     */
    long getRowCount(Category cate, String bookName);

    /**
     * 分页查询数据
     * @param bookName
     * @param category
     * @param pageNow
     * @param pageSize
     * @return
     */
    List<Book> findByPage(String bookName, Category category, Integer pageNow, Integer pageSize);

}
