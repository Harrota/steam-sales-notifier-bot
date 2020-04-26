package com.harrota.service;

import com.harrota.model.App;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.abilitybots.api.sender.MessageSender;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.harrota.Constants.API_URL;
import static com.harrota.Constants.CURRENCY;


public class AppInteractionService {
    private Logger logger = LoggerFactory.getLogger(AppInteractionService.class);
    private final MessageSender sender;
    private App[] apps;
    private final String ENDPOINT = "/appdetails?appids=";
    private AppService appService;


    public AppInteractionService(MessageSender sender) {
        this.sender = sender;
    }

    public static void main(String[] args) throws ParseException, IOException, URISyntaxException {
//
//         AppInteractionService as = new AppInteractionService();
//         App app = new App();
//         String result = as.getApp("https://store.steampowered.com/app/882100/XCOM_Chimera_Squad/");
//         as.jsonToApp(app, "https://store.steampowered.com/app/882100/XCOM_Chimera_Squad/");
//         System.out.println(result);
    }

    public String getAppString(String url) throws ParseException, IOException, URISyntaxException {

        App app = new App();
        StringBuilder sb = new StringBuilder();
        app = jsonToApp(url);
        sb.append(String.format("Found:\n%s \n", app.getName()));
        //  .append(String.format("App ID: %s \n", app.getId()));
        if (app.getInitialPrice() == 0) {
            sb.append(String.format("The game is free\n"));
        } else {
            sb.append(String.format("%s%% OFF! \n", app.getDiscountPercent()))
                    .append(String.format("Price before: %s \n", app.getInitialPrice()))
                    .append(String.format("Price with sale: %s \n", app.getFinalPrice()));
        }
        sb.append(String.format("%s", app.getHeaderImage()));

        System.out.println("get app string for" + app.getName() + " by url " + url);
        return sb.toString();
    }

    //    public void addApp(App app) {
//        appService.add(app);
//    }

    public App jsonToApp(String url) {
        App app = new App();
        app.setAppUrl(url);

        String path = null;
        try {
            path = formatPath(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        url = API_URL + ENDPOINT + path + CURRENCY;
        String genreJson = null;
        try {
            genreJson = IOUtils.toString(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject skr = null;
        try {
            skr = (JSONObject) JSONValue.parseWithException(genreJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<NameValuePair> params = null;
        try {
            params = URLEncodedUtils.parse(new URI(url), StandardCharsets.UTF_8);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Long appid = Long.parseLong((params.get(0).getValue()));//App ID

        Map inid = ((Map) skr.get(appid.toString()));           //Map in ID
        Map indata = ((Map) inid.get("data"));                  //Map in Data
        String name = (String) indata.get("name");
        String headerImage = (String) indata.get("header_image");

        Map priceMap = ((Map) indata.get("price_overview"));


        app.setId(appid);

        app.setName(name);
        app.setHeaderImage(headerImage);

        if (priceMap != null) {
            double initialPrice = ((Long) priceMap.get("initial")).doubleValue() / 100;
            double finalPrice = ((Long) priceMap.get("final")).doubleValue() / 100;
            int discountPercent = ((Long) priceMap.get("discount_percent")).intValue();
            app.setInitialPrice(initialPrice);
            app.setFinalPrice(finalPrice);
            app.setDiscountPercent(discountPercent);
        }
        System.out.println("jsonToApp get " + app.getName() + " by url " + url);
        return app;
    }

    private String formatPath(String url) throws MalformedURLException {
        URL address = new URL(url);
        String path = address.getPath();
        path = path.substring(path.lastIndexOf("app/", '/') + 4);
        path = path.split("/")[0];
        return path;
    }

}
