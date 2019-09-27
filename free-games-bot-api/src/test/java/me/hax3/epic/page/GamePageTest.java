package me.hax3.epic.page;

import me.hax3.epic.model.GameStatus;
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

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static shiver.me.timbers.data.random.RandomIntegers.someInteger;
import static shiver.me.timbers.data.random.RandomStrings.someString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExpectedConditions.class})
public class GamePageTest {

    private WebDriver webDriver;
    private Finders finders;
    private GamePage page;

    @Before
    public void setUp() {
        webDriver = mock(WebDriver.class);
        finders = mock(Finders.class);
        mockStatic(ExpectedConditions.class);
        page = new GamePage(webDriver, finders);
    }

    @Test
    public void Can_get_game_status() {

        final WebElement priceWebElement = mock(WebElement.class);
        final WebElement discountWebElement = mock(WebElement.class);
        final WebElement ownedWebElement = mock(WebElement.class);
        final String price = "$19.99";

        // Given
        when(visibilityOfElementLocated(By.xpath("//button//span[text()='Get' or text()='Owned']"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(webDriver.getCurrentUrl()).willReturn("https://www.epicgames.com/store/en-US/product/conarium/home");
        given(finders.findByText("span", "Free")).willReturn(discountWebElement);
        given(discountWebElement.getText()).willReturn("Free");

        given(webDriver.findElement(By.xpath("//span[text()='Free']/preceding-sibling::s"))).willReturn(priceWebElement);
        given(priceWebElement.getText()).willReturn(price);

        given(webDriver.findElement(By.xpath("//button//span[text()='Get' or text()='Owned']/../.."))).willReturn(ownedWebElement);
        given(ownedWebElement.getText()).willReturn("Get");

        // When
        final GameStatus actual = page.getStatus();

        // Then
        assertThat(actual.getName(), is("conarium"));
        assertThat(actual.getPrice(), is(price));
        assertThat(actual.isDiscount(), is(true));
        assertThat(actual.isOwned(), is(false));
    }

    @Test
    public void Can_get_owned_game_status() {

        final WebElement priceWebElement = mock(WebElement.class);
        final WebElement discountWebElement = mock(WebElement.class);
        final WebElement ownedWebElement = mock(WebElement.class);
        final String price = "$19.99";

        // Given
        when(visibilityOfElementLocated(By.xpath("//button//span[text()='Get' or text()='Owned']"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(webDriver.getCurrentUrl()).willReturn("https://www.epicgames.com/store/en-US/product/conarium/home");
        given(finders.findByText("span", "Free")).willReturn(discountWebElement);
        given(discountWebElement.getText()).willReturn("Free");

        given(webDriver.findElement(By.xpath("//span[text()='Free']/preceding-sibling::s"))).willReturn(priceWebElement);
        given(priceWebElement.getText()).willReturn(price);

        given(webDriver.findElement(By.xpath("//button//span[text()='Get' or text()='Owned']/../.."))).willReturn(ownedWebElement);
        given(ownedWebElement.getText()).willReturn("Owned");

        // When
        final GameStatus actual = page.getStatus();

        // Then
        assertThat(actual.getName(), is("conarium"));
        assertThat(actual.getPrice(), is(price));
        assertThat(actual.isDiscount(), is(true));
        assertThat(actual.isOwned(), is(true));
    }

    @Test
    public void Can_get_not_free_game_status() {

        final WebElement priceWebElement = mock(WebElement.class);
        final WebElement discountWebElement = mock(WebElement.class);
        final WebElement ownedWebElement = mock(WebElement.class);
        final String price = "$19.99";

        // Given
        when(visibilityOfElementLocated(By.xpath("//button//span[text()='Get' or text()='Owned']"))).thenReturn(webDriver_ -> mock(WebElement.class));

        given(webDriver.getCurrentUrl()).willReturn("https://www.epicgames.com/store/en-US/product/conarium/home");
        given(finders.findByText("span", "Free")).willReturn(discountWebElement);
        given(discountWebElement.getText()).willReturn(someString());

        given(webDriver.findElement(By.xpath("//span[text()='Free']/preceding-sibling::s"))).willReturn(priceWebElement);
        given(priceWebElement.getText()).willReturn(price);

        given(webDriver.findElement(By.xpath("//button//span[text()='Get' or text()='Owned']/../.."))).willReturn(ownedWebElement);
        given(ownedWebElement.getText()).willReturn("Owned");

        // When
        final GameStatus actual = page.getStatus();

        // Then
        assertThat(actual.getName(), is("conarium"));
        assertThat(actual.getPrice(), is(price));
        assertThat(actual.isDiscount(), is(false));
        assertThat(actual.isOwned(), is(true));
    }

    @Test
    public void Can_click_get_button() {

        final WebElement button = mock(WebElement.class);

        // Given
        given(webDriver.findElement(By.xpath("//button//span[text()='Get']/../.."))).willReturn(button);

        // When
        page.clickGet();

        // Then
        then(button).should().click();
    }

    @Test
    public void Can_click_free_element_by_index() {

        final int index = 1;
        // Given
        final WebElement webElement = mock(WebElement.class);
        final List<WebElement> elements = asList(mock(WebElement.class), webElement);
        given(webDriver.findElements(By.xpath("//a//span[text()='Free']"))).willReturn(elements);
        elements.get(index);
        // When
        page.clickFree(index);

        // Then
        then(webElement).should().click();
    }

    @Test
    public void Can_get_number_of_free_games() {

        final List games = mock(List.class);
        final Integer expected = someInteger();

        // Given
        when(numberOfElementsToBeMoreThan(By.xpath("//a//span[text()='Free']"), 0)).thenReturn(webDriver1 -> games);
        given(webDriver.findElements(By.xpath("//a//span[text()='Free']"))).willReturn(games);
        given(games.size()).willReturn(expected);

        // When
        final int actual = page.getNumberOfFreeGame();

        // Then
        assertThat(actual, is(expected));
    }

    @Test
    public void Can_click_continue_button_if_it_presented() {

        final By xpathOfContinue = By.xpath("//button/span[text()='Continue']/..");
        final WebElement webElement = mock(WebElement.class);

        // Given
        when(visibilityOfElementLocated(xpathOfContinue)).thenReturn(webDriver1 -> mock(WebElement.class));
        given(webDriver.findElement(xpathOfContinue)).willReturn(webElement);

        // When
        page.clickContinueIfItPresented();

        // Then
        then(webElement).should().click();
    }

    @Test
    public void Can_not_click_continue_button_if_it_not_presented() {

        final By xpathOfContinue = By.xpath("//button/span[text()='Continue']/..");

        // Given
        when(visibilityOfElementLocated(xpathOfContinue)).thenReturn(webDriver1 -> null);

        // When
        page.clickContinueIfItPresented();

        // Then
        then(webDriver).shouldHaveZeroInteractions();
    }
}