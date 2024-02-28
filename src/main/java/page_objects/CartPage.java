package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    By havePromoCodeButton = By.cssSelector(".promo-code-button .collapse-button");
    By promoCodeInput = By.cssSelector("input[name='discount_name']");
    By addPromoCodeButton = By.xpath("//button[@class  ='btn btn-primary']");
    By proceedToCheckoutButton = By.cssSelector(".cart-detailed-actions .btn-primary");
    By cartItems = By.xpath("//li[@class='cart-item']");
    By totalPrice = By.xpath("//span[text()='Total (tax incl.)']/following-sibling::span[@class='value']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage activatePromoCodeInput() {
        driver.findElement(havePromoCodeButton).click();
        return this;
    }

    public CartPage providePromoCode(String promoCode) {
        driver.findElement(promoCodeInput).sendKeys(promoCode);
        return this;
    }

    public CartPage addPromoCode() {
        driver.findElement(addPromoCodeButton).click();
        return this;
    }

    public CheckoutPage goToCheckout() {
        driver.findElement(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }

    public CartPage removeSpecifiedProductFromCart(String productName) {
        var productSpan = driver.findElement(By.xpath("//a[text()='" + productName + "']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        productSpan
                .findElement(By.xpath("./ancestor::li"))
                .findElement(By.xpath(".//i[text()='delete']")).click();


        wait.until(ExpectedConditions.invisibilityOf(productSpan));

        return this;
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getAttribute("innerText");
    }


}
