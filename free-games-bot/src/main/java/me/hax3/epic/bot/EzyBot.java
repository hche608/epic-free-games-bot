package me.hax3.epic.bot;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.GameStatus;
import me.hax3.epic.page.CheckOutPage;
import me.hax3.epic.page.GamePage;
import me.hax3.epic.page.HomePage;
import me.hax3.epic.page.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Arrays.asList;


public class EzyBot {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final LoginPage loginPage;
    private final HomePage homePage;
    private final GamePage gamePage;
    private final CheckOutPage checkOutPage;

    public EzyBot(LoginPage loginPage, HomePage homePage, GamePage gamePage, CheckOutPage checkOutPage) {
        this.loginPage = loginPage;
        this.homePage = homePage;
        this.gamePage = gamePage;
        this.checkOutPage = checkOutPage;
    }

    public List<String> getGames(EpicUser epicUser) {
        homePage.visit();
        homePage.clickSignIn();
        loginPage.loginWithDetail(epicUser);
        homePage.clickStoreFreeGames();
        final GameStatus gameStatus = gamePage.getStatus();
        if (!gameStatus.isOwned() && gameStatus.isDiscount()) {
            gamePage.clickGet();
            checkOutPage.clickCheckout();
        } else {
            log.info("{} is owned", gameStatus.getName());
        }
        return asList(gameStatus.getName());
    }
}
