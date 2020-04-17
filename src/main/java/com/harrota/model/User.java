package com.harrota.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "chatid")
    private int chatID;

    @ManyToMany
    @JoinTable(
            name = "apps_users",
            joinColumns = @JoinColumn (name= "user_id"),
            inverseJoinColumns = @JoinColumn(name= "app_id")
    )
    private List<App> appsList;

    public User(int chatID, List<App> appsList) {
        this.chatID = chatID;
        this.appsList = appsList;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public List<App> getAppsList() {
        return appsList;
    }

    public void setAppsList(List<App> appsList) {
        this.appsList = appsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "chatID=" + chatID +
                ", appsList=" + appsList +
                '}';
    }
}
