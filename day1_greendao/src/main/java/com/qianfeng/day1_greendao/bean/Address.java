package com.qianfeng.day1_greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xray on 16/9/23.
 */
@Entity
public class Address {

    @Id(autoincrement = true)
    private Long id;
    private Long userId;
    private String country;
    private String city;
    private String street;
    @Generated(hash = 1156383252)
    public Address(Long id, Long userId, String country, String city, String street) {
        this.id = id;
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Address(Long userId, String country, String city, String street) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Generated(hash = 388317431)
    public Address() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
