package com.harrota.bot;

import com.harrota.Constants;
import com.harrota.model.App;
import com.harrota.model.User;
import com.harrota.service.AppInteractionService;
import com.harrota.service.AppService;
import com.harrota.service.UserService;
import org.json.simple.parser.ParseException;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.telegram.abilitybots.api.objects.Ability.builder;
import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityMessageCodes.ABILITY_COMMANDS_NOT_FOUND;
import static org.telegram.abilitybots.api.util.AbilityUtils.getLocalizedMessage;


public class NotifierBot extends AbilityBot implements Constants {
    private AppInteractionService appInteractionService;
    private AppService appService = new AppService();
    private UserService userService = new UserService();

    public NotifierBot() {
        super(Constants.BOT_TOKEN, Constants.BOT_NAME);
        appInteractionService = new AppInteractionService(sender);
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
                .name("get")
                .info(Constants.GET_DESCRIPTION)
                .input(1)
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    try {
                        System.out.println("get app for " + ctx.chatId());
                        silent.send(appInteractionService.getAppString(ctx.firstArg()), ctx.chatId());
                    } catch (ParseException | URISyntaxException | IOException e) {
                        e.printStackTrace();
                    }
                })
                .build();
    }

    public Ability addApp() {
        return Ability
                .builder()
                .name("add")
                .info(Constants.ADD_DESCRIPTION)
                .input(1)
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    try {
                        System.out.println("started adding app for " + ctx.chatId());
                        User user = new User();
                        App app = appInteractionService.jsonToApp(ctx.firstArg());

                        user.setChatId(ctx.chatId());
                        appService.saveApp(app);
                        user.addApp(app);
                        userService.saveUser(user);

                        System.out.println("added app for " + ctx.chatId());
                        silent.send(appInteractionService.getAppString(ctx.firstArg()) +
                                "\nSUCCESSFULLY ADDED \nYou can list all your apps by using /list command", ctx.chatId());
                    } catch (IOException | ParseException | URISyntaxException e) {
                        e.printStackTrace();
                    }

                })
                .build();
    }

    public Ability listApp() {
        return builder()
                .name("list")
                .info(Constants.LIST_DESCRIPTION)
                .input(0)
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> {
                    System.out.println("started list for " + ctx.chatId());
                    String result = "Added apps:\n";
                    List<User> users = userService.findUsersByChatId(ctx.chatId());
                    for(User user : users){
                        Set<App> apps = user.getAppSet();

                        for(App app : apps) {
                            result += app.getName() + "\n";
                        }
                    }
                    System.out.println("got list for " + ctx.chatId());
                    silent.send(result, ctx.chatId());
                })
                .build();
    }
}
