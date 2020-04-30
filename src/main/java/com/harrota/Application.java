package com.harrota;

import com.harrota.bot.NotifierBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        SalesNotifier salesNotifier = new SalesNotifier();

        try {
            NotifierBot notifierBot = new NotifierBot();
            botsApi.registerBot(notifierBot);
            salesNotifier.run();
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
