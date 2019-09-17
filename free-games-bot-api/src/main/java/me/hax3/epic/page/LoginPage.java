package me.hax3.epic.page;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import me.hax3.selenium.finders.Finders;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
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

    public void loginWithDetail(EpicUser epicUser) {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//form")));
        final String parent = webDriver.getWindowHandle();

        if (epicUser.getLoginType().equals(LoginType.EPIC)) {
            finders.findById("email").sendKeys(epicUser.getUsername());
            finders.findById("password").sendKeys(epicUser.getPassword());
            clickEpicLogin();
        } else if (epicUser.getLoginType().equals(LoginType.FACEBOOK)) {
            finders.clickById("login-with-facebook");
            switchToNewWindow(parent);
            webDriverWait.until(elementToBeClickable(By.xpath("//div[@id='buttons']/label/input")));
            finders.findById("email").sendKeys(epicUser.getUsername());
            finders.findById("pass").sendKeys(epicUser.getPassword());
            clickFacebookLogin();
            switchWindowBack(parent);
        }
    }

    private void switchWindowBack(String parent) {
        webDriver.switchTo().window(parent);
    }

    public void visit() {
        webDriver.get("https://accounts.epicgames.com/login");
    }

    public void clickRememberMe() {
        finders.clickByText("label", "Remember Me");
    }

    public void clickEpicLogin() {
        webDriverWait.until(elementToBeClickable(By.id("login")));
        finders.clickById("login");
    }

    private void clickFacebookLogin() {

        webDriver.findElement(By.xpath("//div[@id='buttons']/label/input")).click();
    }

    private void switchToNewWindow(String parent) {
        final String newWindow = webDriver.getWindowHandles()
            .stream()
            .filter(s -> !s.equalsIgnoreCase(parent))
            .findFirst()
            .orElseThrow(() -> new NoSuchWindowException("Cannot find parent window."));
        webDriver.switchTo().window(newWindow);
    }
}
