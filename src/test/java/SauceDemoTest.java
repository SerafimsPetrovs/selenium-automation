import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.getConfiguration;

@Slf4j
public class SauceDemoTest {
    public static ChromeDriver driver = new ChromeDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, ofSeconds(getConfiguration().getLong("wait.time")));
    private final String APP_URL = "https://www.saucedemo.com/v1/";

    @Test
    public void loginTest() {
        driver.get(APP_URL);
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");
        WebElement login = driver.findElement(By.id("login-button"));
        login.click();
        wait.until(ExpectedConditions.urlToBe(APP_URL + "inventory.html"));

        ///////////////////////////////////
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, APP_URL + "inventory.html", "Login was NOT successful .");
        ///////////////////////////////////
        WebElement item0 = driver.findElement(By.id("item_0_title_link"));
        item0.click();
        WebElement addToCart = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/v1/inventory-item.html?id=0"));

        ////////////
        String currentUrlAfterItemClick = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlAfterItemClick, "https://www.saucedemo.com/v1/inventory-item.html?id=0", "Item page did not open after clicking ");
        /////////////

        WebElement addToCartButton = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
        addToCartButton.click();

        wait.until(ExpectedConditions.textToBePresentInElement(addToCartButton, "REMOVE"));
        String TextAfter = addToCartButton.getText();
        Assert.assertEquals(TextAfter, "REMOVE", "Text != 'REMOVE' ");
        WebElement Cart = driver.findElement(By.id("shopping_cart_container"));
        Cart.click();
        WebElement checkout = driver.findElement(By.cssSelector("a.btn_action.checkout_button"));
        checkout.click();
        WebElement name = driver.findElement(By.id("first-name"));
        name.sendKeys("Name");
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("lastName");
        WebElement pCode = driver.findElement(By.id("postal-code"));
        pCode.sendKeys("LV-1084");
        WebElement continueButton = driver.findElement(By.cssSelector("input.btn_primary.cart_button"));
        continueButton.click();
        String shopingcontinue = driver.getCurrentUrl();
        Assert.assertEquals(shopingcontinue, "https://www.saucedemo.com/v1/checkout-step-two.html", "Wrong page ");
        ////////////////////
        WebElement ItemName = driver.findElement(By.className("inventory_item_name"));
        String actualText = ItemName.getText();
        String expectedText = "Sauce Labs Bike Light";
        Assert.assertEquals(actualText, expectedText, "Wrong item");
        WebElement finish = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]"));
        finish.click();
        String shopingcomplete = driver.getCurrentUrl();
        Assert.assertEquals(shopingcomplete, "https://www.saucedemo.com/v1/checkout-complete.html", "Wrong page ");
        ////////////////////
        WebElement completeTextElement = driver.findElement(By.className("complete-text"));
        String actual = completeTextElement.getText();
        String expected = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        Assert.assertEquals(actual.trim(), expected.trim(), "something doesn't work");
        System.out.println("");







    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public static void main(String[] args) {
        SauceDemoTest test = new SauceDemoTest();
        test.loginTest();
        test.tearDown();
    }
}
