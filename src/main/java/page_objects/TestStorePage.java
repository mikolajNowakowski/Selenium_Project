package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestStorePage {

    private WebDriver driver;

    By availableProducts = By.xpath("//div[@class='product-description']");


    public TestStorePage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage clickSpecifiedProduct(String productName){
        driver.findElements(availableProducts)
                .stream()
                .filter(webElement -> webElement.findElements(By.xpath("//a[text() = '%s']".formatted(productName))).size()>0)
                .findFirst()
                .ifPresent(webElement -> webElement.findElement(By.xpath("//a[text() = '%s']".formatted(productName))).click());

        return new ProductPage(driver);
    }
}
