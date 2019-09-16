package me.hax3.epic.factory;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class CredentialFactoryTest {


    private String username;
    private String password;
    private CredentialFactory factory;

    @Before
    public void setUp() {
        username = someString();
        password = someString();
        factory = new CredentialFactory(username, password);
    }

    @Test
    public void Can_read_user_from_environment_variables() {

        // Given
        final LoginType loginType = someEnum(LoginType.class);

        // When
        final EpicUser user = factory.read(loginType);

        // Then
        assertThat(user.getLoginType(), is(loginType));
        assertThat(user.getUsername(), is(username));
        assertThat(user.getPassword(), is(password));
    }
}