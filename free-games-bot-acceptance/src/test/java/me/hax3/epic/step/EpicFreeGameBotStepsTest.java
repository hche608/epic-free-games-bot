package me.hax3.epic.step;

import me.hax3.epic.bot.EzyBot;
import me.hax3.epic.factory.CredentialFactory;
import me.hax3.epic.holder.EpicUserHolder;
import me.hax3.epic.holder.GameListHolder;
import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;

public class EpicFreeGameBotStepsTest {

    private CredentialFactory credentialFactory;
    private EpicUserHolder userHolder;
    private EzyBot ezyBot;
    private GameListHolder gameList;
    private EpicFreeGameBotSteps steps;

    @Before
    public void setUp() {
        credentialFactory = mock(CredentialFactory.class);
        userHolder = mock(EpicUserHolder.class);
        ezyBot = mock(EzyBot.class);
        gameList = mock(GameListHolder.class);
        steps = new EpicFreeGameBotSteps(credentialFactory, userHolder, ezyBot, gameList);
    }

    @Test
    public void Can_hold_epic_user() {

        final LoginType loginType = someEnum(LoginType.class);
        final EpicUser user = mock(EpicUser.class);

        // Given
        given(credentialFactory.read(loginType)).willReturn(user);

        // When
        steps.iHaveAnEpicAccountVia(loginType);

        // Then
        then(userHolder).should().set(user);
    }

    @Test
    public void Can_run_lazy_bot() {

        final EpicUser epicUser = mock(EpicUser.class);
        final List purchasedGames = mock(List.class);

        // Given
        given(userHolder.get()).willReturn(epicUser);
        given(ezyBot.getGames(epicUser)).willReturn(purchasedGames);

        // When
        steps.iRanThisEpicFreeGamesBot();

        // Then
        then(ezyBot).should().getGames(epicUser);
        then(gameList).should().set(purchasedGames);
    }
}