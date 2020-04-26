package com.harrota.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Fetch(FetchMode.SELECT)
    @Column(name = "id")
    private Long id;

    @Column(name = "chatid", unique = false)
    @Fetch(FetchMode.SELECT)
    private Long chatId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_app",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "user_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "app_id"))
    private Set<App> apps = new HashSet<>();

    public Set<App> getAppSet () {
        return apps;
    }

    public void setAppSet(Set<App> apps) {
        this.apps = apps;
    }

    public void addApp(App app) {
        apps.add(app);
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }


}
