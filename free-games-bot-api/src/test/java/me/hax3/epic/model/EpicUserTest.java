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
        final String loginMethod = someEnum(LoginType.class).toString();
        final String username = someString();
        final String password = someString();

        // When
        final EpicUser actual = new EpicUser(loginMethod, username, password);

        // Then
        assertThat(actual.getLoginType().toString(), is(loginMethod));
        assertThat(actual.getUsername(), is(username));
        assertThat(actual.getPassword(), is(password));
        assertThat(actual.toString(), is(format("EpicUser{loginType=%s, username=%s, password=******}", loginMethod, username)));
    }

    @Test
    public void Can_create_an_epic_user_with_setter() {

        // Given
        final String loginMethod = someEnum(LoginType.class).toString();
        final String username = someString();
        final String password = someString();

        // When
        final EpicUser actual = new EpicUser();
        actual.setLoginType(loginMethod);
        actual.setUsername(username);
        actual.setPassword(password);

        // Then
        assertThat(actual.getLoginType().toString(), is(loginMethod));
        assertThat(actual.getUsername(), is(username));
        assertThat(actual.getPassword(), is(password));
        assertThat(actual.toString(), is(format("EpicUser{loginType=%s, username=%s, password=******}", loginMethod, username)));
    }
}