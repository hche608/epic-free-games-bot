package me.hax3.epic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class CheckOutPage {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;

    public CheckOutPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public void clickPlaceOrder() {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//button/span[text()='Place Order']")));
        webDriver.findElement(By.xpath("//button/span[text()='Place Order']/..")).click();
        log.info("Clicked Place Order");
    }

    public void close() {
        webDriver.findElement(By.xpath("//div[contains(@class, 'order-summary-container')]//button[contains(@class, 'btn btn-close close')]")).click();
    }
}
