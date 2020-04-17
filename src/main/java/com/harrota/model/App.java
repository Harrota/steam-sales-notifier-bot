package com.harrota.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apps")
public class App {
    @Id
    @Column(name = "appid")
    private Long id;

    @Column(name = "name")
    private String name = "";

    @Column(name = "initialPrice")
    private double initialPrice;

    @Column(name = "finalPrice")
    private double finalPrice;

    @Column(name = "discountPercent")
    private int discountPercent;

    @Column(name = "headerImage")
    private String headerImage = "";

    @ManyToMany
    @JoinTable(name = "apps_users",
            joinColumns = @JoinColumn (name= "app_id"),
            inverseJoinColumns = @JoinColumn(name= "user_id"))
    private List<User> usersList;

    public App() {
    }

    public App(Long id, String name, double initialPrice, double finalPrice, int discountPercent, String headerImage) {
        this.id = id;
        this.name = name;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.discountPercent = discountPercent;
        this.headerImage = headerImage;
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

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialPrice=" + initialPrice +
                ", finalPrice=" + finalPrice +
                ", discountPercent=" + discountPercent +
                ", headerImage='" + headerImage + '\'' +
                ", usersList=" + usersList +
                '}';
    }
}
