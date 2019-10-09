package me.hax3.epic.bot;

import me.hax3.epic.model.EpicUser;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class SpringEzyBotHandler extends SpringBootRequestHandler<EpicUser, String> {
}
