package me.hax3.epic.page;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import me.hax3.selenium.finders.Finders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static shiver.me.timbers.data.random.RandomStrings.someString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExpectedConditions.class})
public class LoginPageTest {

    private WebDriver webDriver;
    private Finders finders;
    private LoginPage page;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        finders = mock(Finders.class);
        mockStatic(ExpectedConditions.class);
        page = new LoginPage(webDriver, finders);
    }

    @Test
    public void Can_visit_login_page() {

        // Given

        // When
        page.visit();

        // Then
        then(webDriver).should().get("https://accounts.epicgames.com/login");
    }

    @Test
    public void Can_enter_login_detail_with_type_of_epic() {

        final EpicUser user = mock(EpicUser.class);
        final String username = someString();
        final String password = someString();
        final WebElement emailWE = mock(WebElement.class);
        final WebElement passwordWE = mock(WebElement.class);

        // Given
        when(visibilityOfElementLocated(By.xpath("//form"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(user.getLoginType()).willReturn(LoginType.EPIC);
        given(user.getUsername()).willReturn(username);
        given(user.getPassword()).willReturn(password);
        given(finders.findById("email")).willReturn(emailWE);
        given(finders.findById("password")).willReturn(passwordWE);

        // When
        page.enterLoginDetail(user);

        // Then
        then(emailWE).should().sendKeys(username);
        then(passwordWE).should().sendKeys(password);

    }

    @Test
    public void Can_enter_login_detail_with_type_of_other() {

        final EpicUser user = mock(EpicUser.class);

        // Given
        when(visibilityOfElementLocated(By.xpath("//form"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(user.getLoginType()).willReturn(LoginType.OTHER);

        // When
        page.enterLoginDetail(user);

        // Then
        then(webDriver).shouldHaveNoMoreInteractions();

    }

    @Test
    public void Can_click_remember_me() {

        // Given

        // When
        page.clickRememberMe();

        // Then
        then(finders).should().clickByText("label", "Remember Me");
    }

    @Test
    public void Can_click_login() {

        // Given

        // When
        page.clickLogin();

        // Then
        then(finders).should().clickById("login");
    }
}