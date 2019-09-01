package me.hax3.epic.holder;

import me.hax3.acceptance.step.GenericHolder;
import me.hax3.epic.model.EpicUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class EpicUserHolder extends GenericHolder<EpicUser> {
}
