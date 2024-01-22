package me.feathers.online.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 图片实体类
 *
 * @author Feathers
 * @create 2017-06-27-10:11
 */
@Entity
@Table(name = "BSIMAGE")
public class Image implements Serializable {

    private static final long serialVersionUID = -2923115370746090257L;

    private Long imgId;
    private String url;
    private String info;


    public Image() {
    }

    public Image(String url, Book book, String info) {
        this.url = url;
        this.info = info;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id")
    @SequenceGenerator(name = "image_id", sequenceName = "image_id")
    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return imgId != null ? imgId.equals(image.imgId) : image.imgId == null;
    }

    @Override
    public int hashCode() {
        return imgId != null ? imgId.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Image{");
        sb.append("imgId=").append(imgId);
        sb.append(", url='").append(url).append('\'');
        sb.append(", info='").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
