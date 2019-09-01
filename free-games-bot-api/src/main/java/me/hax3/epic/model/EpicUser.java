package me.hax3.epic.model;

public class EpicUser {

    public EpicUser(LoginType loginType, String username, String password) {
        this.loginType = loginType;
        this.username = username;
        this.password = password;
    }

    private LoginType loginType;

    private String username;

    private String password;

    public LoginType getLoginType() {
        return loginType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "EpicUser{" +
            "loginMethod='" + loginType + '\'' +
            ", username='" + username + '\'' +
            '}';
    }
}
