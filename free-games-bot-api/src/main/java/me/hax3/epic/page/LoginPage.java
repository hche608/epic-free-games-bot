package me.hax3.epic.page;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
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
    private final WebDriverWait webDriverWait;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public void loginWithDetail(EpicUser epicUser) {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//form")));
        final java.lang.String parent = webDriver.getWindowHandle();

        if (epicUser.getLoginType().equals(LoginType.EPIC)) {
            webDriver.findElement(By.id("email")).sendKeys(epicUser.getUsername());
            webDriver.findElement(By.id("password")).sendKeys(epicUser.getPassword());
            clickEpicLogin();
        } else if (epicUser.getLoginType().equals(LoginType.FACEBOOK)) {
            webDriver.findElement(By.id("login-with-facebook")).click();
            switchToNewWindow(parent);
            webDriverWait.until(elementToBeClickable(By.xpath("//div[@id='buttons']/label/input")));
            webDriver.findElement(By.id("email")).sendKeys(epicUser.getUsername());
            webDriver.findElement(By.id("pass")).sendKeys(epicUser.getPassword());
            clickFacebookLogin();
            switchWindowBack(parent);
        }
    }

    private void switchWindowBack(java.lang.String parent) {
        webDriver.switchTo().window(parent);
    }

    public void visit() {
        webDriver.get("https://accounts.epicgames.com/login");
    }

    public void clickRememberMe() {
        webDriver.findElement(By.xpath("//label[text()='Remember Me']")).click();
    }

    public void clickEpicLogin() {
        webDriverWait.until(elementToBeClickable(By.id("login")));
        webDriver.findElement(By.id("login")).click();
    }

    private void clickFacebookLogin() {

        webDriver.findElement(By.xpath("//div[@id='buttons']/label/input")).click();
    }

    private void switchToNewWindow(java.lang.String parent) {
        final java.lang.String newWindow = webDriver.getWindowHandles()
            .stream()
            .filter(s -> !s.equalsIgnoreCase(parent))
            .findFirst()
            .orElseThrow(() -> new NoSuchWindowException("Cannot find parent window."));
        webDriver.switchTo().window(newWindow);
    }
}
