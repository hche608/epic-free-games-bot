package me.hax3.epic.bot.functions;

import me.hax3.epic.bot.EzyBot;
import me.hax3.epic.model.EpicUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class SpringEzyBot implements Function<EpicUser, String> {

    private final EzyBot ezyBot;

    public SpringEzyBot(EzyBot ezyBot) {
        this.ezyBot = ezyBot;
    }

    @Override
    public String apply(EpicUser epicUser) {
        final List<String> games = ezyBot.getGames(epicUser);
        return "Job's done.";
    }
}
