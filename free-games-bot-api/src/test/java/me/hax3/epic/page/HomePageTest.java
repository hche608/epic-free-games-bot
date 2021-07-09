package me.hax3.epic.page;


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

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExpectedConditions.class})
public class HomePageTest {

    private WebDriver webDriver;
    private HomePage page;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        mockStatic(ExpectedConditions.class);
        page = new HomePage(webDriver);
    }

    @Test
    public void Can_visit_home_page() {

        // Given

        // When
        page.visit();

        // Then
        then(webDriver).should().get("https://www.epicgames.com/store");
    }

    @Test
    public void Can_click_free_game_collection() {

        final WebElement webElement = mock(WebElement.class);

        // Given
        when(visibilityOfElementLocated(By.xpath("//a[contains(@href, '/store/en-US/collection/free-game-collection')]"))).thenReturn(webDriver1 -> mock(WebElement.class));
        given(webDriver.findElement(By.xpath("//a[contains(@href, '/store/en-US/collection/free-game-collection')]"))).willReturn(webElement);

        // When
        page.clickFreeGamesCollection();

        // Then
        then(webElement).should().click();
    }

    @Test
    public void Can_click_free_now() {

        final WebElement webElement = mock(WebElement.class);

        // Given
        when(visibilityOfElementLocated(By.xpath("//a[contains(@aria-label,'Free Now')]"))).thenReturn(webDriver1 -> mock(WebElement.class));
        given(webDriver.findElement(By.xpath("//a[contains(@aria-label,'Free Now')]"))).willReturn(webElement);

        // When
        page.clickFreeNow();

        // Then
        then(webElement).should().click();
    }
}