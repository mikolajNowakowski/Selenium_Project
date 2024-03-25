package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;

public class TestStorePage {

    private WebDriver driver;

    By availableProducts = By.xpath("//div[@class='product-description']");
    By homeAccessoriesButton = By.id("category-8");
    By accessoriesButton = By.id("category-6");
    By cartButton = By.xpath("//div[@id='_desktop_cart']/div/div");


    public TestStorePage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage clickSpecifiedProduct(String productName) {
        driver.findElements(availableProducts)
                .stream()
                .filter(webElement -> webElement.findElements(By.xpath(".//a[text() = '%s']".formatted(productName))).size() > 0)
                .findFirst()
                .ifPresent(webElement -> webElement.findElement(By.xpath("//a[text() = '%s']".formatted(productName))).click());

        return new ProductPage(driver);
    }

    public TestStorePage goToHomeAccessories() {
        var actions = new Actions(driver);

        actions.moveToElement(driver.findElement(accessoriesButton)).perform();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.elementToBeClickable(homeAccessoriesButton)).click();
        return this;
    }

    public BigDecimal addProductsToCartBySpecifiedName(String pattern) throws InterruptedException {
        var productsToAdd = driver.findElements(availableProducts)
                .stream()
                .filter(webElement -> webElement.findElements(By.xpath(".//a[contains(text(),'%s')]".formatted(pattern))).size() > 0)
                .map(webElement -> webElement.findElement(By.xpath(".//a[contains(text(),'%s')]".formatted(pattern))).getText())
                .toList();

        BigDecimal price = BigDecimal.ZERO;

        for (var productName : productsToAdd) {
            driver.findElement(By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'%s')]".formatted(productName.toLowerCase()))).click();
            var productPage = new ProductPage(driver);
            price = price.add(productPage.getPriceOfProduct());
            productPage
                    .clickAddToCartButton()
                    .clickContinueShoppingBtn()
                    .clickMyStoreImg()
                    .goToHomeAccessories();
        }
        return price;
    }

    public CartPage goToCart() {
        driver.findElement(cartButton).click();
        return new CartPage(driver);
    }

}
