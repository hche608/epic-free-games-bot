package me.hax3.epic.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import me.hax3.epic.bot.EzyBot;
import me.hax3.epic.factory.CredentialFactory;
import me.hax3.epic.holder.EpicUserHolder;
import me.hax3.epic.holder.GameListHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class EpicFreeGameBotSteps {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CredentialFactory credentialFactory;
    private final EpicUserHolder userHolder;
    private final EzyBot ezyBot;
    private final GameListHolder gameList;

    public EpicFreeGameBotSteps(CredentialFactory credentialFactory, EpicUserHolder userHolder, EzyBot ezyBot, GameListHolder gameList) {
        this.credentialFactory = credentialFactory;
        this.userHolder = userHolder;
        this.ezyBot = ezyBot;
        this.gameList = gameList;
    }

    @Given("^I have an epic account via \"([^\"]*)\"$")
    public void iHaveAnEpicAccountVia(String loginType) {
        userHolder.set(credentialFactory.read(loginType));
    }

    @When("I ran this epic free games bot")
    public void iRanThisEpicFreeGamesBot() {
        gameList.set(ezyBot.getGames(userHolder.get()));
    }

    @Then("the games are acquired")
    public void theGamesAreAcquired() {
        final List<String> listOfGame = gameList.get();
        assertThat(listOfGame.size(), greaterThan(0));
        listOfGame.forEach(log::info);
    }

    @Then("do nothing")
    public void doNothing() {
        log.info("You already have the game.");
    }
}
