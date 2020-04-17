package com.harrota.bot;

import com.harrota.Constants;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.telegram.abilitybots.api.objects.Ability.builder;
import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityMessageCodes.ABILITY_COMMANDS_NOT_FOUND;
import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;
import static org.telegram.abilitybots.api.util.AbilityUtils.getLocalizedMessage;

public class NotifierBot extends AbilityBot implements Constants {
    public NotifierBot() {
    super(Constants.BOT_TOKEN, Constants.BOT_NAME);

    }

    @Override
    public int creatorId() {
        return Constants.CREATOR_ID;
    }

    public Ability helpCommand() {
        return builder()
                .name("help")
                .info(Constants.HELP_DESCRIPTION)
                .locality(ALL)
                .privacy(PUBLIC)
                .input(0)
                .action(ctx -> {
                    String commands = "Available commands:\n";
                    commands += abilities().values().stream()
                            .filter(ability -> nonNull(ability.info()))
                            .map(ability -> {
                                String name = ability.name();
                                String info = ability.info();
                                return format("/%s - %s", name, info);
                            })
                            .sorted()
                            .reduce((a, b) -> format("%s%n%s", a, b))
                            .orElse(getLocalizedMessage(ABILITY_COMMANDS_NOT_FOUND, ctx.user().getLanguageCode()));

                    silent.send(commands, ctx.chatId());
                })
                .build();
    }
    public Ability getApp() {
        return Ability
                .builder()
                .name("add")
                .info(Constants.ADD_DESCRIPTION)
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    silent.send("Hello world!", ctx.chatId());

                })

                .build();
    }
}
