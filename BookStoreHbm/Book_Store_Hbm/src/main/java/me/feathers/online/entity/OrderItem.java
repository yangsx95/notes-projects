package me.feathers.online.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 订单详情
 *
 * @author Feathers
 * @create 2017-06-27-10:07
 */
@Entity
@Table(name = "BSORDERITEM")
public class OrderItem implements Serializable{

    private static final long serialVersionUID = -4036615809462109471L;

    private Long itemId;
    private String book_isbn;
    private String book_name;
    private String book_author;
    private int count;
    private double price;
    private Order order;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public OrderItem() {
    }

    public OrderItem(String book_isbn, String book_name, String book_author, int count, double price, Order order, String imageUrl) {
        this.book_isbn = book_isbn;
        this.book_name = book_name;
        this.book_author = book_author;
        this.count = count;
        this.price = price;
        this.order = order;
        this.imageUrl = imageUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id")
    @SequenceGenerator(name = "order_item_id", sequenceName = "order_item_id")
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem item = (OrderItem) o;

        if (itemId != null ? !itemId.equals(item.itemId) : item.itemId != null) return false;
        return book_isbn != null ? book_isbn.equals(item.book_isbn) : item.book_isbn == null;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (book_isbn != null ? book_isbn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("itemId=").append(itemId);
        sb.append(", book_isbn='").append(book_isbn).append('\'');
        sb.append(", book_name='").append(book_name).append('\'');
        sb.append(", book_author='").append(book_author).append('\'');
        sb.append(", count=").append(count);
        sb.append(", price=").append(price);
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
