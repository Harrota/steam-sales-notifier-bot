package com.harrota.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apps")
public class App {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name = "";

    @Column(name = "headerURL")
    private String headerURL = "";

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

    public App(int id, String name, String headerURL, double initialPrice, double finalPrice, int discountPercent, String headerImage) {
        this.id = id;
        this.name = name;
        this.headerURL = headerURL;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
        this.discountPercent = discountPercent;
        this.headerImage = headerImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderURL() {
        return headerURL;
    }

    public void setHeaderURL(String headerURL) {
        this.headerURL = headerURL;
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

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headerURL='" + headerURL + '\'' +
                ", initialPrice=" + initialPrice +
                ", finalPrice=" + finalPrice +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
