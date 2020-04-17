package com.harrota.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.harrota.APIConnectionHelper;
import com.harrota.model.App;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static com.harrota.Constants.API_URL;

public class AppService {
    //  private final MessageSender sender;
    private App[] apps;
    private final String CURRENCY = "&cc=ru&l=ru";
    private final String ENDPOINT = "/appdetails?appids=";

    public AppService() {
    }

//    public AppService(MessageSender sender) {
//        this.sender = sender;
//    }

    public static void main(String[] args) throws ParseException, IOException, URISyntaxException {
        AppService as = new AppService();
        App app = new App();
        as.jsonToApp(app, "https://store.steampowered.com/app/882100/XCOM_Chimera_Squad/");
        System.out.println(app);
    }

    private App jsonToApp(App app, String url) throws IOException, ParseException, URISyntaxException {
        String path = formatPath(url);

        url = API_URL + ENDPOINT + path + CURRENCY;
        String genreJson = IOUtils.toString(new URL(url));
        JSONObject skr = (JSONObject) JSONValue.parseWithException(genreJson);

        List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), StandardCharsets.UTF_8);
        Long appid = Long.parseLong((params.get(0).getValue()));//App ID

        Map inid = ((Map) skr.get(appid.toString()));           //Map in ID
        Map indata = ((Map) inid.get("data"));                  //Map in Data
        String name = (String) indata.get("name");
        String headerImage = (String) indata.get("header_image");

        Map priceMap = ((Map) indata.get("price_overview"));

        app.setId(appid);
        app.setName(name);
        app.setHeaderImage(headerImage);

        if(priceMap != null) {
            double initialPrice = ((Long) priceMap.get("initial")).doubleValue() / 100;
            double finalPrice = ((Long) priceMap.get("final")).doubleValue() / 100;
            int discountPercent = ((Long) priceMap.get("discount_percent")).intValue();
            app.setInitialPrice(initialPrice);
            app.setFinalPrice(finalPrice);
            app.setDiscountPercent(discountPercent);
        }
        return app;
    }

    private String formatPath(String url) throws MalformedURLException {
        URL address = new URL(url);
        String path = address.getPath();
        path = path.substring(path.lastIndexOf("app/",'/') + 4);
        path = path.split("/")[0];
        return path;
    }

    private String getIDFromURL(String url) {
        return url;
    }

}
