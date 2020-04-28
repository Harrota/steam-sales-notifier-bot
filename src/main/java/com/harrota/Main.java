package com.harrota;

import com.harrota.model.App;
import com.harrota.model.User;
import com.harrota.service.AppService;
import com.harrota.service.UserService;

public class Main {
    public static void main(String[] args){
        AppService appService = new AppService();
        UserService userService = new UserService();

        App app = new App();
        app.setId(22L);
        app.setName("Test");
        appService.saveApp(app);

        App app1 = new App();
        app1.setId(32L);
        app1.setName("SecondTest");
        appService.saveApp(app1);



        User user = new User();
        user.setId(1L);
        user.addApp(app);
        user.addApp(app1);
        userService.saveUser(user);
    }
}