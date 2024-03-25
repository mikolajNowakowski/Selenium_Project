package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    By havePromoCodeButton = By.cssSelector(".promo-code-button .collapse-button");
    By promoCodeInput = By.cssSelector("input[name='discount_name']");
    By addPromoCodeButton = By.xpath("//button[@class  ='btn btn-primary']");
    By proceedToCheckoutButton = By.cssSelector(".cart-detailed-actions .btn-primary");
    By cartItems = By.xpath("//li[@class='cart-item']");
    By totalPrice = By.xpath("//span[text()='Total (tax incl.)']/following-sibling::span[@class='value']");
    By totalPriceWithoutShipping = By.cssSelector("div#cart-subtotal-products > .value");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage activatePromoCodeInput() {
        driver.findElement(havePromoCodeButton).click();
        return this;
    }

    public CartPage providePromoCode(String promoCode) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(promoCodeInput)).sendKeys(promoCode);

        //driver.findElement(promoCodeInput).sendKeys(promoCode);
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

    public BigDecimal getTotalPrice() {
        return convertPriceToBD(driver.findElement(totalPrice).getAttribute("innerText"));
    }

    public BigDecimal getTotalPriceWithoutShipping(){
        return convertPriceToBD(driver.findElement(totalPriceWithoutShipping).getAttribute("innerText"));
    }


    private BigDecimal convertPriceToBD(String price){
        return new BigDecimal(price.replaceAll("[^\\d.,]", ""));
    }

}
