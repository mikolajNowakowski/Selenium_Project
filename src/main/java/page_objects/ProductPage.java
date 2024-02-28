package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    By increaseQuantityByOneButton = By.cssSelector(".bootstrap-touchspin-up.btn.btn-touchspin.js-touchspin > .material-icons.touchspin-up");

    By addToCartButton = By.cssSelector(".add-to-cart.btn.btn-primary");

    By proceedToCheckoutButton = By.cssSelector(".cart-content-btn .btn-primary");

    By continueShoppingButton = By.cssSelector(".btn.btn-secondary");

    By myStoreImg = By.cssSelector("img[alt='teststore']");

    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ProductPage increaseQuantityOfProductByButton() {
        driver.findElement(increaseQuantityByOneButton).click();
        return this;
    }

    public ProductPage clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
        return this;
    }

    public ProductPage clickContinueShoppingBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton)).click();
        return this;
    }

    public TestStorePage clickMyStoreImg() {
        driver.findElement(myStoreImg).click();
        return new TestStorePage(driver);
    }


    public CartPage clickGoToCheckoutButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutButton)).click();
        return new CartPage(driver);
    }
}
