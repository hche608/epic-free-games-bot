package me.hax3.epic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BasePage {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    private final Actions actions;

    public BasePage(WebDriver webDriver) {
        this(webDriver, new WebDriverWait(webDriver, 8), new Actions(webDriver));
    }

    BasePage(WebDriver webDriver, WebDriverWait webDriverWait, Actions actions) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.actions = actions;
    }

    public void clickSignIn() {
        if (!webDriver.findElement(By.id("user")).getAttribute("class").contains("sign-in")) {
            final WebElement user = webDriver.findElement(By.xpath("//div[@id='user']/a"));
            final WebElement logout = webDriver.findElement(By.xpath("//a/span[text()='Sign Out']"));
            actions
                .moveToElement(user)
                .moveToElement(logout)
                .click()
                .build()
                .perform();
        }
        final By xpath = By.xpath("//span[text()='Sign In']");
        webDriverWait.until(elementToBeClickable(xpath));
        webDriver.findElement(xpath).click();
    }

    public void clickStore() {
        final By xpath = By.xpath("//a/p[text()='Store']");
        webDriverWait.until(elementToBeClickable(xpath));
        webDriver.findElement(xpath).click();
    }
}
