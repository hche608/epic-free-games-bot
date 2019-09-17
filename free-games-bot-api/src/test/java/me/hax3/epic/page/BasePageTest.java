package me.hax3.epic.page;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static shiver.me.timbers.data.random.RandomStrings.someString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExpectedConditions.class})
public class BasePageTest {

    private WebDriver webDriver;
    private BasePage basePage;
    private Actions actions;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        mockStatic(ExpectedConditions.class);
        actions = mock(Actions.class);
        basePage = new BasePage(webDriver, mock(WebDriverWait.class), actions);
    }

    @Test
    public void Can_click_store() {

        final WebElement store = mock(WebElement.class);

        // Given
        given(webDriver.findElement(By.xpath("//a/p[text()='Store']"))).willReturn(store);

        // When
        basePage.clickStore();

        // Then
        then(store).should().click();
    }

    @Test
    public void Can_click_sign_in() {

        final WebElement user = mock(WebElement.class);
        final WebElement signIn = mock(WebElement.class);

        // Given
        given(webDriver.findElement(By.id("user"))).willReturn(user);
        given(user.getAttribute("class")).willReturn("sign-in");
        when(elementToBeClickable(By.xpath("//span[text()='Sign In']"))).thenReturn(webDriver_ -> mock(WebElement.class));
        given(webDriver.findElement(By.xpath("//span[text()='Sign In']"))).willReturn(signIn);

        // When
        basePage.clickSignIn();

        // Then
        then(signIn).should().click();
    }

    @Test
    public void Can_click_sign_in_if_needs_logout() {

        final WebElement user = mock(WebElement.class);
        final WebElement signIn = mock(WebElement.class);
        final WebElement userALink = mock(WebElement.class);
        final WebElement signOut = mock(WebElement.class);
        final Actions moveToUserALink = mock(Actions.class);
        final Actions movedToSignOut = mock(Actions.class);
        final Actions click = mock(Actions.class);
        final Action actionBuild = mock(Action.class);

        // Given
        given(webDriver.findElement(By.id("user"))).willReturn(user);
        given(user.getAttribute("class")).willReturn(someString());
        given(webDriver.findElement(By.xpath("//div[@id='user']/a"))).willReturn(userALink);
        given(webDriver.findElement(By.xpath("//a/span[text()='Sign Out']"))).willReturn(signOut);
        given(actions.moveToElement(userALink)).willReturn(moveToUserALink);
        given(moveToUserALink.moveToElement(signOut)).willReturn(movedToSignOut);
        given(movedToSignOut.click()).willReturn(click);
        given(click.build()).willReturn(actionBuild);

        when(elementToBeClickable(By.xpath("//span[text()='Sign In']"))).thenReturn(webDriver_ -> mock(WebElement.class));
        given(webDriver.findElement(By.xpath("//span[text()='Sign In']"))).willReturn(signIn);

        // When
        basePage.clickSignIn();

        // Then
        then(actionBuild).should().perform();
        then(signIn).should().click();
    }
}