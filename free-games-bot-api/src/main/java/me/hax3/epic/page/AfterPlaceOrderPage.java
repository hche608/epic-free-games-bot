package me.hax3.epic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class AfterPlaceOrderPage extends BasePage {

    private final WebDriverWait webDriverWait;

    public AfterPlaceOrderPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public void waitForThankYouForBuying() {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//span[text()='Thank you for buying']")));
    }
}
