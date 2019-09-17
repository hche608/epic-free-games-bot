package me.hax3.epic.page;

import me.hax3.epic.model.GameStatus;
import me.hax3.selenium.finders.Finders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class GamePage extends BasePage {
    private final WebDriver webDriver;
    private final Finders finders;
    private final WebDriverWait webDriverWait;

    public GamePage(WebDriver webDriver, Finders finders) {
        super(webDriver);
        this.webDriver = webDriver;
        this.finders = finders;
        webDriverWait = new WebDriverWait(webDriver, 30);
    }

    public GameStatus getStatus() {
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//button//span[text()='Get' or text()='Owned']")));
        final String discount = finders.findByText("span", "Free").getText();
        final String price = webDriver.findElement(By.xpath("//span[text()='Free']/preceding-sibling::s")).getText();
        final String owned = webDriver.findElement(By.xpath("//button//span[text()='Get' or text()='Owned']/../..")).getText();

        return new GameStatus(parseGameName(webDriver.getCurrentUrl()), price, parseDiscount(discount), parseOwned(owned));
    }

    public void clickGet() {
        webDriver.findElement(By.xpath("//button//span[text()='Get']/../..")).click();
    }

    private String parseGameName(String url) {
        return url
            .replace("https://www.epicgames.com/store/en-US/product/", "")
            .replace("/home", "");
    }

    private boolean parseDiscount(String discount) {
        return discount.equalsIgnoreCase("Free");
    }

    private boolean parseOwned(String owned) {
        return owned.equalsIgnoreCase("OWNED");
    }
}
