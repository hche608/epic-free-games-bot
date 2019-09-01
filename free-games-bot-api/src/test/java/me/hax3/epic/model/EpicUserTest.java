package me.hax3.epic.model;

import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static shiver.me.timbers.data.random.RandomEnums.someEnum;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class EpicUserTest {

    @Test
    public void Can_create_an_epic_user() {

        // Given
        final LoginType loginMethod = someEnum(LoginType.class);
        final String username = someString();
        final String password = someString();

        // When
        final EpicUser actual = new EpicUser(loginMethod, username, password);

        // Then
        assertThat(actual.getLoginType(), is(loginMethod));
        assertThat(actual.getUsername(), is(username));
        assertThat(actual.getPassword(), is(password));
        assertThat(actual.toString(), is(format("EpicUser{loginMethod='%s', username='%s'}", loginMethod, username)));
    }
}