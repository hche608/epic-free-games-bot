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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExpectedConditions.class})
public class AfterPlaceOrderPageTest {

    private WebDriver webDriver;
    private AfterPlaceOrderPage page;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        mockStatic(ExpectedConditions.class);
        page = new AfterPlaceOrderPage(webDriver);
    }

    @Test
    public void Can_wait_for_thank_you_for_buying() {

        // Given
        when(visibilityOfElementLocated(By.xpath("//span[text()='Thank you for buying']"))).thenReturn(webDriver1 -> mock(WebElement.class));

        // When
        page.waitForThankYouForBuying();

        // Then
    }
}