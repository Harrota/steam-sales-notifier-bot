package com.harrota.service;

import com.harrota.model.App;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.harrota.Constants.API_URL;
import static com.harrota.Constants.CURRENCY;
import static org.junit.jupiter.api.Assertions.*;

class AppInteractionServiceTest {

    /*
        Testing formatting from store URL to api URL
    */
    @Test
    void testFormatPath() throws MalformedURLException {
        String ENDPOINT = "/appdetails?appids=";
        URL address = new URL("https://store.steampowered.com/app/730/CounterStrike_Global_Offensive");
        String path = address.getPath();

        path = path.substring(path.lastIndexOf("app/", '/') + 4);
        path = path.split("/")[0];

        String url = API_URL + ENDPOINT + path + CURRENCY;
        assertEquals("https://store.steampowered.com/api/appdetails?appids=730&cc=ru&l=ru", url);
    }

    /*
        Testing converting from store URL to App
    */
    @Test
    void jsonToAppTest() {
        AppInteractionService appInteractionService = new AppInteractionService();
        Long expectedId = 730L;
        String expectedUrl = "https://store.steampowered.com/app/730/CounterStrike_Global_Offensive/";
        String expectedName = "Counter-Strike: Global Offensive";
        App app = appInteractionService.jsonToApp("https://store.steampowered.com/app/730/CounterStrike_Global_Offensive/");

        assertEquals(expectedId, app.getAppId());
        assertEquals(expectedUrl, app.getAppUrl());
        assertEquals(expectedName, app.getName());
    }
}