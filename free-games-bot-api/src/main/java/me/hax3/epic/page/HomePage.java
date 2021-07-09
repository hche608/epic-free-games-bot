package me.hax3.epic.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class HomePage extends BasePage {

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public void visit() {
        webDriver.get("https://www.epicgames.com/store");
    }

    public void clickFreeGamesCollection() {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//a[contains(@href, '/store/en-US/collection/free-game-collection')]")));
        webDriver.findElement(By.xpath("//a[contains(@href, '/store/en-US/collection/free-game-collection')]")).click();
    }

    public void clickFreeNow() {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//a[contains(@aria-label,'Free Now')]")));
        webDriver.findElement(By.xpath("//a[contains(@aria-label,'Free Now')]")).click();
    }
}