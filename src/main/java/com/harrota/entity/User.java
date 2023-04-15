package com.harrota.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_apps",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_id"))
    private List<App> apps;

    private String name;

    private String surname;

    private LocalDate created;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", created=" + created +
                '}';
    }
}
