package me.hax3.epic;

import me.hax3.epic.bot.EzyBot;
import me.hax3.epic.page.CheckOutPage;
import me.hax3.epic.page.GamePage;
import me.hax3.epic.page.HomePage;
import me.hax3.epic.page.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class EpicBotConfiguration {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private HomePage homePage;

    @Autowired
    private GamePage gamePage;

    @Autowired
    private CheckOutPage checkOutPage;

    @Bean
    public EzyBot ezyBot() {
        return new EzyBot(loginPage, homePage, gamePage, checkOutPage);
    }

}
