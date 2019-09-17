package me.hax3.epic.bot;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.GameStatus;
import me.hax3.epic.page.CheckOutPage;
import me.hax3.epic.page.GamePage;
import me.hax3.epic.page.HomePage;
import me.hax3.epic.page.LoginPage;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

public class EzyBotTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private GamePage gamePage;
    private CheckOutPage checkOutagePage;
    private EzyBot ezyBot;

    @Before
    public void setUp() {
        loginPage = mock(LoginPage.class);
        homePage = mock(HomePage.class);
        gamePage = mock(GamePage.class);
        checkOutagePage = mock(CheckOutPage.class);
        ezyBot = new EzyBot(loginPage, homePage, gamePage, checkOutagePage);
    }

    @Test
    public void can_get_free_games() {

        final EpicUser epicUser = mock(EpicUser.class);
        final GameStatus gameStatus = mock(GameStatus.class);

        // Given
        given(gamePage.getStatus()).willReturn(gameStatus);
        given(gameStatus.isDiscount()).willReturn(true);
        given(gameStatus.isOwned()).willReturn(false);

        // When
        final List<String> actual = ezyBot.getGames(epicUser);

        // Then
        then(homePage).should().visit();
        then(homePage).should().clickSignIn();
        then(loginPage).should().loginWithDetail(epicUser);
        then(homePage).should().clickStoreFreeGames();
        then(gamePage).should().getStatus();
        then(gamePage).should().clickGet();
        then(checkOutagePage).should().clickCheckout();

        assertThat(actual.size(), greaterThan(0));
    }

    @Test
    public void can_ignore_owned_free_games() {

        final EpicUser epicUser = mock(EpicUser.class);
        final GameStatus gameStatus = mock(GameStatus.class);

        // Given
        given(gamePage.getStatus()).willReturn(gameStatus);
        given(gameStatus.isDiscount()).willReturn(true);
        given(gameStatus.isOwned()).willReturn(true);

        // When
        final List<String> actual = ezyBot.getGames(epicUser);

        // Then
        then(homePage).should().visit();
        then(homePage).should().clickSignIn();
        then(loginPage).should().loginWithDetail(epicUser);
        then(homePage).should().clickStoreFreeGames();
        then(gamePage).should().getStatus();
        then(gamePage).should(never()).clickGet();
        then(checkOutagePage).shouldHaveZeroInteractions();

        assertThat(actual.size(), greaterThan(0));
    }

    @Test
    public void can_ignore_not_free_games() {

        final EpicUser epicUser = mock(EpicUser.class);
        final GameStatus gameStatus = mock(GameStatus.class);

        // Given
        given(gamePage.getStatus()).willReturn(gameStatus);
        given(gameStatus.isDiscount()).willReturn(false);
        given(gameStatus.isOwned()).willReturn(false);

        // When
        final List<String> actual = ezyBot.getGames(epicUser);

        // Then
        then(homePage).should().visit();
        then(homePage).should().clickSignIn();
        then(loginPage).should().loginWithDetail(epicUser);
        then(homePage).should().clickStoreFreeGames();
        then(gamePage).should().getStatus();
        then(gamePage).should(never()).clickGet();
        then(checkOutagePage).shouldHaveZeroInteractions();

        assertThat(actual.size(), greaterThan(0));
    }
}