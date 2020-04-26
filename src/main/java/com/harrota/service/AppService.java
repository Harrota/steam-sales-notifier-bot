package com.harrota.service;

import com.harrota.dao.AppDAO;
import com.harrota.model.App;
import com.harrota.model.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.harrota.Constants.API_URL;
import static com.harrota.Constants.CURRENCY;

public class AppService {
    private AppDAO appDAO = new AppDAO();

    public AppService() {
    }

    public App findApp(int id) {
        return appDAO.findById(id);
    }

    public void saveApp(App app) {
        appDAO.save(app);
    }

    public void deleteApp(App app) {
        appDAO.delete(app);
    }

    public void updateApp(App app) {
        appDAO.update(app);
    }

    public List<App> findAllApps() {
        return appDAO.findAll();
    }
    private final String ENDPOINT = "/appdetails?appids=";
    public User findUserById(Long id) {
        return appDAO.findUserById(id);
    }
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
