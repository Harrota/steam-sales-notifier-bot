package com.harrota;

import com.harrota.bot.NotifierBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Application {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            NotifierBot notifierBot = new NotifierBot();
            botsApi.registerBot(notifierBot);
        }catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
