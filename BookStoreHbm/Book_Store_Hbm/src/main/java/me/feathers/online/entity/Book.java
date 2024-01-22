package me.feathers.online.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 图书实体类
 *
 * @author Feathers
 * @create 2017-06-27-10:01
 */
@Entity
@Table(name = "BSBOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = -3387659216969275966L;

    private Long bookId;
    private String isbn;
    private String name;
    private String author;
    private String publisher;
    private Date publish_date;
    private double oldPrice;
    private double newPrice;
    private String author_loc;
    private Suit suit;
    private Category category;
    private String info;
    private Image image;

    public Book() {
    }

    public Book(String isbn, String name, String author, String publisher, Date publish_date,
                double oldPrice, double newPrice, String author_loc, Suit suit, Category category,
                String info, Image image) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publish_date = publish_date;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.author_loc = author_loc;
        this.suit = suit;
        this.category = category;
        this.info = info;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bs_bookId")
    @SequenceGenerator(name = "bs_bookId", sequenceName = "bs_bookId", allocationSize = 1,
            initialValue = 9)
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Column(nullable = false)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Temporal(TemporalType.DATE)
    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getAuthor_loc() {
        return author_loc;
    }

    public void setAuthor_loc(String author_loc) {
        this.author_loc = author_loc;
    }

    @Enumerated(EnumType.STRING)
    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @OneToOne
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return bookId != null ? bookId.equals(book.bookId) : book.bookId == null;
    }

    @Override
    public int hashCode() {
        return bookId != null ? bookId.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Book{");
        sb.append("bookId=").append(bookId);
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", publish_date=").append(publish_date);
        sb.append(", oldPrice=").append(oldPrice);
        sb.append(", newPrice=").append(newPrice);
        sb.append(", author_loc='").append(author_loc).append('\'');
        sb.append(", suit=").append(suit);
        sb.append(", category=").append(category);
        sb.append(", info='").append(info).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
