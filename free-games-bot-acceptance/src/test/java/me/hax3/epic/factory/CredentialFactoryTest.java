package me.hax3.epic.factory;

import me.hax3.epic.model.LoginType;
import org.junit.Before;
import org.junit.Test;

import static shiver.me.timbers.data.random.RandomEnums.someEnum;

public class CredentialFactoryTest {


    private CredentialFactory factory;

    @Before
    public void setUp() {
        factory = new CredentialFactory();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void Can_read_user_from_environment_variables() {

        // Given

        // When
        factory.read(someEnum(LoginType.class));

        // Then
    }
}