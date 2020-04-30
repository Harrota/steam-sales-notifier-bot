package com.harrota.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "apps")
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Fetch(FetchMode.SELECT)
    @Column(name = "id")
    private Long id;


    @Column(name = "appid")
    @Fetch(FetchMode.SELECT)
    private Long appid;

    @Column(name = "name")
    @Fetch(FetchMode.SELECT)
    private String name;

    @Column(name = "initialPrice")
    private double initialPrice;

    @Column(name = "finalPrice")
    private double finalPrice;

    @Column(name = "discountPercent")
    private int discountPercent;

    @Column(name = "headerImage")
    private String headerImage;

    @Column(name = "appUrl")
    private String appUrl;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_app",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<User>();

    public List<User> getUsersSet() {
        return users;
    }

    public void setUserSet(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public App() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Long getAppId() {
        return appid;
    }

    public void setAppId(Long appid) {
        this.appid = appid;
    }
}
