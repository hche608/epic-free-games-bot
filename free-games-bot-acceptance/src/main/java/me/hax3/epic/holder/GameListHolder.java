package me.hax3.epic.holder;

import me.hax3.acceptance.steps.GenericHolder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("cucumber-glue")
public class GameListHolder extends GenericHolder<List<String>> {
}
