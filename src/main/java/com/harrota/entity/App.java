package com.harrota.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "apps")
public class App {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    private String steamId;

    private Double initialPrice;

    private Double discountPrice;

    private Integer discountPercent;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "apps")
    private List<User> users;

    private LocalDate created;

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", steamId='" + steamId + '\'' +
                ", initialPrice=" + initialPrice +
                ", discountPrice=" + discountPrice +
                ", discountPercent=" + discountPercent +
                ", created=" + created +
                '}';
    }
}
