package me.hax3.epic.factory;

import me.hax3.epic.model.EpicUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CredentialFactory {


    private final Map<String, String> username;
    private final Map<String, String> password;

    public CredentialFactory(@Value("#{${username}}") Map<String, String> username, @Value("#{${password}}") Map<String, String> password) {
        this.username = username;
        this.password = password;
    }

    public EpicUser read(String loginType) {
        return new EpicUser(loginType, username.get(loginType.toLowerCase()), password.get(loginType.toLowerCase()));
    }
}
