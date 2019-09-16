package me.hax3.epic.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class BasePage {

    private final WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickSignIn() {
        webDriver.findElement(By.xpath("//span[text()='Sign In']")).click();
    }

    public void clickStore() {
        webDriver.findElement(By.xpath("//a/p[text()='Store']")).click();
    }
}
