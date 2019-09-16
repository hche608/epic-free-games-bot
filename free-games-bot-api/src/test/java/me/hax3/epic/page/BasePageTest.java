package me.hax3.epic.page;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class BasePageTest {

    private WebDriver webDriver;
    private BasePage basePage;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        basePage = new BasePage(webDriver);
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

        final WebElement signIn = mock(WebElement.class);

        // Given
        given(webDriver.findElement(By.xpath("//span[text()='Sign In']"))).willReturn(signIn);

        // When
        basePage.clickSignIn();

        // Then
        then(signIn).should().click();
    }
}