package me.hax3.epic;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class EpicBotShutdownConfiguration {

    @Autowired
    private WebDriver webDriver;

    @PreDestroy
    public void quitWebDriver() {
        webDriver.quit();
    }

}
