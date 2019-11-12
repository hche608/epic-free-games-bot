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

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;
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
    public void Can_click_free_game() {

        final WebElement webElement = mock(WebElement.class);

        // Given
        when(numberOfElementsToBeMoreThan(By.id("freeGames"), 0)).thenReturn(webDriver_ -> anyList());
        given(webDriver.findElement(By.xpath("//div[@id='freeGames']/following-sibling::*[2]/div/a"))).willReturn(webElement);

        // When
        page.clickFreeGames();

        // Then
        then(webElement).should().click();
    }
}