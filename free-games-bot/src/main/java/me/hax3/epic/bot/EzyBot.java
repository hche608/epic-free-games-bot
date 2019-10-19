package me.hax3.epic.bot;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.GameStatus;
import me.hax3.epic.page.AfterPlaceOrderPage;
import me.hax3.epic.page.CheckOutPage;
import me.hax3.epic.page.GamePage;
import me.hax3.epic.page.HomePage;
import me.hax3.epic.page.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EzyBot {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final LoginPage loginPage;
    private final HomePage homePage;
    private final GamePage gamePage;
    private final CheckOutPage checkOutPage;
    private final AfterPlaceOrderPage afterPlaceOrderPage;

    public EzyBot(LoginPage loginPage, HomePage homePage, GamePage gamePage, CheckOutPage checkOutPage, AfterPlaceOrderPage afterPlaceOrderPage) {
        this.loginPage = loginPage;
        this.homePage = homePage;
        this.gamePage = gamePage;
        this.checkOutPage = checkOutPage;
        this.afterPlaceOrderPage = afterPlaceOrderPage;
    }

    public List<String> getGames(EpicUser epicUser) {
        homePage.visit();
        log.info("landed on homepage");
        homePage.clickSignIn();
        log.info("clicked sign in");
        loginPage.loginWithDetail(epicUser);
        log.info("logged in");
        homePage.clickStoreFreeGames();
        log.info("looking for games");
        final ArrayList<String> games = new ArrayList<>();
        final int numberOfFreeGame = gamePage.getNumberOfFreeGame();
        if (numberOfFreeGame > 1) {
            for (int i = 0; i < numberOfFreeGame; i++) {
                gamePage.clickFree(i);
                games.add(getGame());
                homePage.clickStore();
                homePage.clickStoreFreeGames();
            }
        } else {
            games.add(getGame());
        }
        return games;
    }

    private String getGame() {
        gamePage.clickContinueIfItPresented();
        final GameStatus gameStatus = gamePage.getStatus();
        if (!gameStatus.isOwned() && gameStatus.isDiscount()) {
            gamePage.clickGet();
            checkOutPage.clickPlaceOrder();
            afterPlaceOrderPage.waitForThankYouForBuying();
            return gameStatus.getName();
        } else {
            while(gamePage.getNumberOfAddons() > 0){
                gamePage.clickGetAddon();
                checkOutPage.clickPlaceOrder();
                afterPlaceOrderPage.waitForThankYouForBuying();
                homePage.clickStore();
                homePage.clickStoreFreeGames();
            }

            return gameStatus.getName() + " is owned.";
        }
    }
}
