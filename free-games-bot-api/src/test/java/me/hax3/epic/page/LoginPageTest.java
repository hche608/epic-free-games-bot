package me.hax3.epic.page;

import me.hax3.epic.model.EpicUser;
import me.hax3.epic.model.LoginType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.openqa.selenium.WebDriver.TargetLocator;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static shiver.me.timbers.data.random.RandomStrings.someString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExpectedConditions.class})
public class LoginPageTest {

    private WebDriver webDriver;
    private LoginPage page;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        mockStatic(ExpectedConditions.class);
        page = new LoginPage(webDriver);
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
    public void Can_login_with_epic_user() {

        final EpicUser user = mock(EpicUser.class);
        final String username = someString();
        final String password = someString();
        final WebElement emailWebElement = mock(WebElement.class);
        final WebElement passwordWebElement = mock(WebElement.class);
        final WebElement loginWebElement = mock(WebElement.class);

        // Given
        when(visibilityOfElementLocated(By.xpath("//form"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(webDriver.getWindowHandle()).willReturn(someString());

        given(user.getLoginType()).willReturn(LoginType.EPIC);
        given(user.getUsername()).willReturn(username);
        given(user.getPassword()).willReturn(password);
        given(webDriver.findElement(By.id("email"))).willReturn(emailWebElement);
        given(webDriver.findElement(By.id("password"))).willReturn(passwordWebElement);
        given(webDriver.findElement(By.id("login"))).willReturn(loginWebElement);
        when(elementToBeClickable(By.id("login"))).thenReturn(webDriver_ -> mock(WebElement.class));

        // When
        page.loginWithDetail(user);

        // Then
        then(emailWebElement).should().sendKeys(username);
        then(passwordWebElement).should().sendKeys(password);
        then(loginWebElement).should().click();

    }

    @Test
    public void Can_login_with_facebook_user() {

        final EpicUser user = mock(EpicUser.class);
        final String parent = someString();
        final String newWindow = someString();
        final Set<String> windowHandles = Stream.of(parent, newWindow).collect(Collectors.toSet());
        final String username = someString();
        final String password = someString();
        final WebElement loginWithFacebook = mock(WebElement.class);
        final WebElement emailWebElement = mock(WebElement.class);
        final WebElement passwordWebElement = mock(WebElement.class);
        final TargetLocator targetLocator = mock(TargetLocator.class);
        final WebElement facebookLogin = mock(WebElement.class);


        // Given
        when(visibilityOfElementLocated(By.xpath("//form"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(webDriver.getWindowHandle()).willReturn(parent);

        given(user.getLoginType()).willReturn(LoginType.FACEBOOK);
        given(user.getUsername()).willReturn(username);
        given(user.getPassword()).willReturn(password);
        given(webDriver.findElement(By.id("login-with-facebook"))).willReturn(loginWithFacebook);
        given(webDriver.getWindowHandles()).willReturn(windowHandles);
        given(webDriver.switchTo()).willReturn(targetLocator);
        when(elementToBeClickable(By.xpath("//div[@id='buttons']/label/input"))).thenReturn(webDriver_ -> mock(WebElement.class));


        given(webDriver.findElement(By.id("email"))).willReturn(emailWebElement);
        given(webDriver.findElement(By.id("pass"))).willReturn(passwordWebElement);
        given(webDriver.findElement(By.xpath("//div[@id='buttons']/label/input"))).willReturn(facebookLogin);

        // When
        page.loginWithDetail(user);

        // Then
        then(loginWithFacebook).should().click();
        then(targetLocator).should().window(newWindow);
        then(emailWebElement).should().sendKeys(username);
        then(passwordWebElement).should().sendKeys(password);
        then(facebookLogin).should().click();

    }

    @Test(expected = NoSuchWindowException.class)
    public void Can_fail_login_with_facebook_user() {

        final EpicUser user = mock(EpicUser.class);
        final String parent = someString();
        final String newWindow = someString();
        final Set<String> windowHandles = Stream.of(parent).collect(Collectors.toSet());
        final WebElement loginWithFacebook = mock(WebElement.class);
        final String username = someString();
        final String password = someString();
        final WebElement emailWebElement = mock(WebElement.class);
        final WebElement passwordWebElement = mock(WebElement.class);
        final TargetLocator targetLocator = mock(TargetLocator.class);
        final WebElement facebookLogin = mock(WebElement.class);


        // Given
        when(visibilityOfElementLocated(By.xpath("//form"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(webDriver.getWindowHandle()).willReturn(parent);

        given(user.getLoginType()).willReturn(LoginType.FACEBOOK);
        given(user.getUsername()).willReturn(username);
        given(user.getPassword()).willReturn(password);
        given(webDriver.getWindowHandles()).willReturn(windowHandles);
        given(webDriver.findElement(By.id("login-with-facebook"))).willReturn(loginWithFacebook);

        // When
        page.loginWithDetail(user);

        // Then
        then(loginWithFacebook).should().click();
        then(webDriver).should(never()).switchTo();
        then(facebookLogin).should(never()).click();

    }

    @Test
    public void Can_login_with_other_user() {

        final EpicUser user = mock(EpicUser.class);

        // Given
        when(visibilityOfElementLocated(By.xpath("//form"))).thenReturn(webDriver_ -> mock(WebElement.class));
        given(webDriver.getWindowHandle()).willReturn(someString());

        given(user.getLoginType()).willReturn(LoginType.OTHER);

        // When
        page.loginWithDetail(user);

        // Then
        then(webDriver).should().getWindowHandle();
        then(webDriver).shouldHaveNoMoreInteractions();

    }

    @Test
    public void Can_click_remember_me() {

        final WebElement webElement = mock(WebElement.class);

        // Given
        given(webDriver.findElement(By.xpath("//label[text()='Remember Me']"))).willReturn(webElement);

        // When
        page.clickRememberMe();

        // Then
        then(webElement).should().click();
    }

    @Test
    public void Can_click_login() {

        final WebElement login = mock(WebElement.class);

        // Given
        when(elementToBeClickable(By.id("login"))).thenReturn(webDriver_ -> mock(WebElement.class));
        given(webDriver.findElement(By.id("login"))).willReturn(login);

        // When
        page.clickEpicLogin();

        // Then
        then(login).should().click();
    }
}