package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page_objects.HomePage;
import page_objects.TestStorePage;

public class AddAllMugsToBasketTest extends BaseTest {


    @Test
    public void allNonCustomizableMugsToBasketTest() throws InterruptedException {
        var priceOfProductsAddedToCart = new HomePage(driver)
                .closeCookieWarning()
                .goToTestStore()
                .goToHomeAccessories()
                .addProductsToCartBySpecifiedName("Mug");

        var totalPriceInCart = new TestStorePage(driver)
                .goToCart()
                .getTotalPriceWithoutShipping();

        Assert.assertEquals(priceOfProductsAddedToCart.stripTrailingZeros(),totalPriceInCart.stripTrailingZeros());
        Thread.sleep(5000);
    }

}
