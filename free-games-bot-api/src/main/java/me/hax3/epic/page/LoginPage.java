package me.hax3.epic.page;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import me.hax3.selenium.finders.Finders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class LoginPage extends BasePage {

    private final WebDriver webDriver;
    private final Finders finders;
    private final WebDriverWait webDriverWait;

    public LoginPage(WebDriver webDriver, Finders finders) {
        super(webDriver);
        this.webDriver = webDriver;
        this.finders = finders;
        this.webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public void enterLoginDetail(EpicUser epicUser) {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//form")));

        if (epicUser.getLoginType().equals(LoginType.EPIC)) {
            finders.findById("email").sendKeys(epicUser.getUsername());
            finders.findById("password").sendKeys(epicUser.getPassword());
        }
    }

    public void visit() {
        webDriver.get("https://accounts.epicgames.com/login");
    }

    public void clickRememberMe() {
        finders.clickByText("label", "Remember Me");
    }

    public void clickLogin() {
        finders.clickById("login");
    }
}
