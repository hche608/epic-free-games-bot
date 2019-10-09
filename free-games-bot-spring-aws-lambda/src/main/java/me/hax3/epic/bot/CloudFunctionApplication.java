package me.hax3.epic.bot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static java.lang.String.format;

@SpringBootApplication(scanBasePackages = {"me.hax3.epic.page", "me.hax3.epic.bot"})
public class CloudFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFunctionApplication.class, args);
    }

    @Bean
    public WebDriver webDriver(@Value("${web.driver:chrome-aws-lambda}") String webDriver) {
        if ("chrome".equals(webDriver)) {
            return new ChromeDriver();
        }

        if ("chrome-headless".equals(webDriver)) {
            final ChromeOptions options = headlessChromeOptions();
            return new ChromeDriver(options);
        }

        if ("chrome-aws-lambda".equals(webDriver)) {
            final ChromeOptions options = headlessChromeOptions();
            options.setBinary("/opt/headless-chromium");
            options.addArguments("--disable-gpu");
            return new ChromeDriver(options);
        }

        throw new IllegalArgumentException(format("Web driver %s not supported.", webDriver));
    }

    private static ChromeOptions headlessChromeOptions() {
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        options.addArguments("--hide-scrollbars");
        options.addArguments("--mute-audio");

        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-client-side-phishing-detection");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-hang-monitor");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-prompt-on-repost");
        options.addArguments("--disable-sync");
        options.addArguments("--disable-translate");
        options.addArguments("--metrics-recording-only");
        options.addArguments("--no-first-run");
        options.addArguments("--safebrowsing-disable-auto-update");

        options.addArguments("--no-cache");
        options.addArguments("--user-data-dir=/tmp/user-data");
        options.addArguments("--single-process");
        options.addArguments("--data-path=/tmp/data-path");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--homedir=/tmp");
        options.addArguments("--disk-cache-dir=/tmp/cache-dir");
        options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        return options;
    }
}