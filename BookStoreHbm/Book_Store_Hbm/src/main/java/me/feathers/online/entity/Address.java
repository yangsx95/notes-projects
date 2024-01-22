package me.feathers.online.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 收货地址对象
 *
 * @author Feathers
 * @create 2017-06-27-9:43
 */
@Entity
@Table(name = "BSADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = 564085031239424915L;

    /**
     * 地址的id
     */
    private Long addressId;

    /**
     * 区域
     */
    private String area;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮编
     */
    private String emailCode;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 是否为默认收货地址，是0 否1
     */
    private String isDefault;

    /**
     * 收货地址所属用户
     */
    private User user;

    public Address() {
    }

    public Address(String area, String detailAddress, String emailCode, String receiver, String tel, String isDefault) {
        this.area = area;
        this.detailAddress = detailAddress;
        this.emailCode = emailCode;
        this.receiver = receiver;
        this.tel = tel;
        this.isDefault = isDefault;
    }

    public Address(String area, String detailAddress, String emailCode, String receiver, String tel, String isDefault, User user) {
        this.area = area;
        this.detailAddress = detailAddress;
        this.emailCode = emailCode;
        this.receiver = receiver;
        this.tel = tel;
        this.isDefault = isDefault;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id")
    @SequenceGenerator(name = "address_id", sequenceName = "address_id")
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Column(nullable = false)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(nullable = false)
    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Column(nullable = false)
    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    @Column(nullable = false)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTel() {
        return tel;
    }

    @Column(nullable = false)
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(nullable = false)
    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return addressId != null ? addressId.equals(address.addressId) : address.addressId == null;
    }

    @Override
    public int hashCode() {
        return addressId != null ? addressId.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Address{");
        sb.append("addressId=").append(addressId);
        sb.append(", area='").append(area).append('\'');
        sb.append(", detailAddress='").append(detailAddress).append('\'');
        sb.append(", emailCode='").append(emailCode).append('\'');
        sb.append(", receiver='").append(receiver).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", isDefault='").append(isDefault).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
