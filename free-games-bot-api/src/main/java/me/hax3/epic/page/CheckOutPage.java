package me.hax3.epic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class CheckOutPage {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public CheckOutPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public void clickCheckout() {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//button/span[text()='Checkout']")));
        webDriver.findElement(By.xpath("//button/span[text()='Checkout']/..")).click();
    }
}
