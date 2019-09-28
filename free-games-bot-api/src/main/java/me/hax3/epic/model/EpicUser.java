package me.hax3.epic.model;

public class EpicUser {

    private LoginType loginType;
    private String username;
    private String password;

    public EpicUser() {
    }

    public EpicUser(String loginType, String username, String password) {
        this.loginType = LoginType.valueOf(loginType.toUpperCase());
        this.username = username;
        this.password = password;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = LoginType.valueOf(loginType);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EpicUser{" +
            "loginType=" + loginType.toString() +
            ", username=" + username +
            ", password=" + "******" +
            '}';
    }
}