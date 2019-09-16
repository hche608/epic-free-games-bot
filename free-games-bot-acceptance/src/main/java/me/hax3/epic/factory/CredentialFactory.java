package me.hax3.epic.factory;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CredentialFactory {
    private final String username;
    private final String password;

    public CredentialFactory(@Value("${epic.username}") String username, @Value("${epic.password}") String password) {
        this.username = username;
        this.password = password;
    }

    public EpicUser read(LoginType loginType) {
        return new EpicUser(loginType, username, password);
    }
}
