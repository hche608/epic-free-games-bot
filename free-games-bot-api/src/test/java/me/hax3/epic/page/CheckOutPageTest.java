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
public class CheckOutPageTest {

    private WebDriver webDriver;
    private CheckOutPage page;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        mockStatic(ExpectedConditions.class);
        page = new CheckOutPage(webDriver);
    }

    @Test
    public void Can_click_checkout_button() {

        final WebElement button = mock(WebElement.class);

        // Given
        when(visibilityOfElementLocated(By.xpath("//button/span[text()='Checkout']"))).thenReturn(webDriver_ -> mock(WebElement.class));
        given(webDriver.findElement(By.xpath("//button/span[text()='Checkout']/.."))).willReturn(button);

        // When
        page.clickCheckout();

        // Then
        then(button).should().click();
    }
}