package me.hax3.epic.bot;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.hax3.epic.model.EpicUser;
import me.hax3.epic.page.AfterPlaceOrderPage;
import me.hax3.epic.page.CheckOutPage;
import me.hax3.epic.page.GamePage;
import me.hax3.epic.page.HomePage;
import me.hax3.epic.page.LoginPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EzyBotHandler implements RequestHandler<EpicUser, String> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final EzyBot ezyBot;
    private final WebDriver webDriver;

    public EzyBotHandler() {
        this(new ChromeOptions()
//          headless setting start
                .setBinary("/opt/headless-chromium")
                .addArguments("--headless")
                .addArguments("--disable-gpu")
                .addArguments("--hide-scrollbars")
                .addArguments("--mute-audio")
//          headless setting end
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-extensions")
                .addArguments("--no-sandbox")
                .addArguments("--disable-background-networking")
                .addArguments("--disable-background-timer-throttling")
                .addArguments("--disable-client-side-phishing-detection")
                .addArguments("--disable-default-apps")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-extensions")
                .addArguments("--disable-hang-monitor")
                .addArguments("--disable-popup-blocking")
                .addArguments("--disable-prompt-on-repost")
                .addArguments("--disable-sync")
                .addArguments("--disable-translate")
                .addArguments("--metrics-recording-only")
                .addArguments("--no-first-run")
                .addArguments("--safebrowsing-disable-auto-update")

                .addArguments("--no-cache")
                .addArguments("--user-data-dir=/tmp/user-data")
                .addArguments("--single-process")
                .addArguments("--data-path=/tmp/data-path")
                .addArguments("--ignore-certificate-errors")
                .addArguments("--homedir=/tmp")
                .addArguments("--disk-cache-dir=/tmp/cache-dir")
                .addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
        );
    }

    EzyBotHandler(ChromeOptions options) {
        this(new ChromeDriver(options));
    }

    EzyBotHandler(WebDriver webDriver) {
        this(new EzyBot(new LoginPage(webDriver), new HomePage(webDriver), new GamePage(webDriver), new CheckOutPage(webDriver), new AfterPlaceOrderPage(webDriver)), webDriver);

    }

    EzyBotHandler(EzyBot ezyBot, WebDriver webDriver) {
        this.ezyBot = ezyBot;
        this.webDriver = webDriver;
    }

    @Override
    public String handleRequest(EpicUser epicUser, Context context) {
        final Capabilities capabilities = ((RemoteWebDriver) webDriver).getCapabilities();
        log.info("chrome version is {}", capabilities.getVersion());
        log.info("{}", epicUser);
        ezyBot.getGames(epicUser).forEach(log::info);
        return "Job's Done";
    }

}
