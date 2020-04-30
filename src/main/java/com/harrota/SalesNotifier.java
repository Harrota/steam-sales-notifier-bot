package com.harrota;

import com.harrota.model.App;
import com.harrota.model.User;
import com.harrota.service.AppInteractionService;
import com.harrota.service.AppService;
import com.harrota.service.UserService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

import static com.harrota.Constants.BOT_TOKEN;

public class SalesNotifier implements Runnable {
    @Override
    public void run() {
        SalesNotifier sn = new SalesNotifier();
        synchronized (sn) {
            while (true) {
                try {
                    sn.sendPost();
                    sn.wait(3600000 * 20);
                    System.out.println("Waited 20h");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void sendPost() throws Exception {
        AppInteractionService appInteractionService = new AppInteractionService();
        AppService appService = new AppService();
        UserService userService = new UserService();


        List<User> users = userService.findAllUsers();
        for (User user : users) {
            List<App> userAppList = user.getAppList();
            for (App app : userAppList) {
                App newApp = appInteractionService.jsonToApp(app.getAppUrl());
                System.out.println("For user " + user.getChatId() + " app " + newApp.getName() + " is " + newApp.getDiscountPercent() + "% OFF");
                if (newApp.getDiscountPercent() != 0) {
                    HttpPost post = new HttpPost("https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage");
                    List<NameValuePair> urlParameters = new ArrayList<>();
                    String result = "";
                    result += newApp.getName() + "\nis now for sale!\n" + newApp.getDiscountPercent() + "% OFF!\n  " + (int) app.getInitialPrice() + " RUB - before\n  " + (int) app.getFinalPrice() + " RUB - now\n\n";

                    // add request parameters
                    urlParameters.add(new BasicNameValuePair("chat_id", user.getChatId().toString()));
                    urlParameters.add(new BasicNameValuePair("text", result));
                    post.setEntity(new UrlEncodedFormEntity(urlParameters));

                    System.out.println(post);

                    try (CloseableHttpClient httpClient = HttpClients.createDefault();
                         CloseableHttpResponse response = httpClient.execute(post)) {
                        System.out.println(EntityUtils.toString(response.getEntity()));
                    }

                }
            }
        }





    }
}

