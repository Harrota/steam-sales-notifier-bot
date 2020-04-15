package com.harrota.bot;

import com.harrota.Constants;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.toggle.AbilityToggle;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class NotifierBot extends AbilityBot implements Constants {
    public NotifierBot() {
    super(Constants.BOT_TOKEN, Constants.BOT_NAME);

    }

    @Override
    public int creatorId() {
        return Constants.CREATOR_ID;
    }

}
