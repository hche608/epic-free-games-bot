package me.hax3.epic.bot;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.GameStatus;
import me.hax3.epic.page.AfterPlaceOrderPage;
import me.hax3.epic.page.CheckOutPage;
import me.hax3.epic.page.GamePage;
import me.hax3.epic.page.HomePage;
import me.hax3.epic.page.LoginPage;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class EzyBotTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private GamePage gamePage;
    private CheckOutPage checkOutPage;
    private EzyBot ezyBot;
    private AfterPlaceOrderPage afterPlaceOrderPage;

    @Before
    public void setUp() {
        loginPage = mock(LoginPage.class);
        homePage = mock(HomePage.class);
        gamePage = mock(GamePage.class);
        checkOutPage = mock(CheckOutPage.class);
        afterPlaceOrderPage = mock(AfterPlaceOrderPage.class);
        ezyBot = new EzyBot(loginPage, homePage, gamePage, checkOutPage, afterPlaceOrderPage);
    }

    @Test
    public void can_get_a_free_game() {

        final EpicUser epicUser = mock(EpicUser.class);
        final GameStatus gameStatus = mock(GameStatus.class);
        final int numberOfGame = 1;

        // Given
        given(gamePage.getNumberOfFreeGame()).willReturn(numberOfGame);
        given(gamePage.getStatus()).willReturn(gameStatus);
        given(gameStatus.isDiscount()).willReturn(true);
        given(gameStatus.isOwned()).willReturn(false);

        // When
        final List<String> actual = ezyBot.getGames(epicUser);

        // Then
        then(homePage).should().visit();
        then(homePage).should().clickSignIn();
        then(loginPage).should().loginWithDetail(epicUser);
        then(homePage).should().clickFreeGames();
        then(gamePage).should().getNumberOfFreeGame();
        then(gamePage).should().getStatus();
        then(gamePage).should().clickGet();
        then(checkOutPage).should().clickPlaceOrder();
        then(afterPlaceOrderPage).should().waitForThankYouForBuying();
        assertThat(actual.size(), is(numberOfGame));
    }

    @Test
    public void can_get_a_free_game_with_addons() {

        final EpicUser epicUser = mock(EpicUser.class);
        final GameStatus gameStatus = mock(GameStatus.class);
        final int numberOfGame = 1;
        final int numberOfAddons = 1;

        // Given
        given(gamePage.getNumberOfFreeGame()).willReturn(numberOfGame);
        given(gamePage.getStatus()).willReturn(gameStatus);
        given(gameStatus.isDiscount()).willReturn(true);
        given(gameStatus.isOwned()).willReturn(true);
        given(gamePage.getNumberOfAddons()).willReturn(numberOfAddons, 0);

        // When
        final List<String> actual = ezyBot.getGames(epicUser);

        // Then
        then(homePage).should().visit();
        then(homePage).should().clickSignIn();
        then(loginPage).should().loginWithDetail(epicUser);
        then(homePage).should(times(2)).clickFreeGames();
        then(gamePage).should().getNumberOfFreeGame();
        then(gamePage).should().getStatus();
        then(gamePage).should().clickGetAddon();
        then(checkOutPage).should().clickPlaceOrder();
        then(afterPlaceOrderPage).should().waitForThankYouForBuying();
        then(homePage).should().clickStore();
        then(homePage).shouldHaveNoMoreInteractions();
        then(loginPage).shouldHaveNoMoreInteractions();
        then(checkOutPage).shouldHaveNoMoreInteractions();
        then(afterPlaceOrderPage).shouldHaveNoMoreInteractions();
        assertThat(actual.size(), is(numberOfGame));
    }

    @Test
    public void Can_get_free_game_collection() {

        final EpicUser epicUser = mock(EpicUser.class);
        final int numberOfGame = 2;
        final GameStatus gameStatus_1 = mock(GameStatus.class);
        final GameStatus gameStatus_2 = mock(GameStatus.class);

        // Given
        given(gamePage.getNumberOfFreeGame()).willReturn(numberOfGame);

        given(gamePage.getStatus()).willReturn(gameStatus_1);
        given(gameStatus_1.isDiscount()).willReturn(true);
        given(gameStatus_1.isOwned()).willReturn(false);

        given(gamePage.getStatus()).willReturn(gameStatus_2);
        given(gameStatus_2.isDiscount()).willReturn(true);
        given(gameStatus_2.isOwned()).willReturn(false);

        // When
        final List<String> actual = ezyBot.getGames(epicUser);

        // Then
        then(homePage).should().visit();
        then(homePage).should().clickSignIn();
        then(loginPage).should().loginWithDetail(epicUser);
        then(homePage).should(times(numberOfGame + 1)).clickFreeGames();
        then(gamePage).should().getNumberOfFreeGame();
        then(gamePage).should().clickFree(0);
        then(gamePage).should(times(2)).getStatus();
        then(gamePage).should(times(numberOfGame)).clickGet();
        then(checkOutPage).should(times(numberOfGame)).clickPlaceOrder();
        then(afterPlaceOrderPage).should(times(numberOfGame)).waitForThankYouForBuying();
        then(gamePage).should().clickFree(1);
        assertThat(actual.size(), is(numberOfGame));
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
        then(homePage).should().clickFreeGames();
        then(gamePage).should().getStatus();
        then(gamePage).should(never()).clickGet();
        then(checkOutPage).shouldHaveZeroInteractions();

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
        then(homePage).should().clickFreeGames();
        then(gamePage).should().getStatus();
        then(gamePage).should(never()).clickGet();
        then(checkOutPage).shouldHaveZeroInteractions();

        assertThat(actual.size(), greaterThan(0));
    }
}