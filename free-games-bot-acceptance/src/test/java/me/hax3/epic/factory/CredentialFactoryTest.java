package me.hax3.epic.factory;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class CredentialFactoryTest {


    private Map usernameMap;
    private Map passwordMap;
    private CredentialFactory factory;

    @Before
    public void setUp() {
        usernameMap = mock(Map.class);
        passwordMap = mock(Map.class);
        factory = new CredentialFactory(usernameMap, passwordMap);
    }

    @Test
    public void Can_read_user_from_environment_variables() {

        final String loginType = someEnum(LoginType.class).toString();
        final String username = someString();
        final String password = someString();

        // Given
        given(usernameMap.get(loginType.toLowerCase())).willReturn(username);
        given(passwordMap.get(loginType.toLowerCase())).willReturn(password);

        // When
        final EpicUser user = factory.read(loginType);

        // Then
        assertThat(user.getLoginType().toString(), is(loginType));
        assertThat(user.getUsername(), is(username));
        assertThat(user.getPassword(), is(password));
    }
}