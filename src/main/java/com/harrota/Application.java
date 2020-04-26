package com.harrota;

import com.harrota.bot.NotifierBot;
import com.harrota.model.App;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        DBService dbService = new DBService();

        dbService.run();
        try {
            NotifierBot notifierBot = new NotifierBot();
            botsApi.registerBot(notifierBot);
            notifierBot.getApp();
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
