package com.harrota.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Fetch(FetchMode.SELECT)
    @Column(name = "id")
    private Long id;

    @Column(name = "chatid")
    @Fetch(FetchMode.SELECT)
    private Long chatId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_app",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_id"))
    private List<App> apps = new ArrayList<>();

    public List<App> getAppList() {
        return apps;
    }

    public void setAppList(List<App> apps) {
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
