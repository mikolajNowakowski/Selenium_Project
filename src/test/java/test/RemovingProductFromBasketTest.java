package test;

import model.customer.data.removing_product_from_basket.RemovingOneOfTwoProductFromBasket;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page_objects.HomePage;
import utilities.FileReaders;

import java.lang.reflect.Method;
import java.math.BigDecimal;


@Listeners({BaseTest.class})

public class RemovingProductFromBasketTest extends BaseTest {

    @Test(dataProvider = "removingOneOfTwoProductProvider")
    void removingOneOfTwoProductFromBasket(RemovingOneOfTwoProductFromBasket data) throws InterruptedException {
        extentTest.pass(data.toString());
        var price = new HomePage(driver)
                .closeCookieWarning()
                .goToTestStore()
                .clickSpecifiedProduct(data.getFirstProductName())
                .increaseQuantityOfProductByButton()
                .clickAddToCartButton()
                .clickContinueShoppingBtn()
                .clickMyStoreImg()
                .clickSpecifiedProduct(data.getRemovedProductName())
                .clickAddToCartButton()
                .clickGoToCheckoutButton()
                .removeSpecifiedProductFromCart(data.getRemovedProductName())
                .getTotalPrice();
        Assert.assertEquals(price, new BigDecimal(data.getExpectedPrice()));
    }

    @DataProvider
    private Object[] removingOneOfTwoProductProvider(Method method) {
        var props = FileReaders.propertiesLoader(appPropertiesPath);
        var data = FileReaders.loadExcelData(/*"./src/main/resources/RemovingProductFromBasket.xlsx"*/props.getProperty("exelFileName"), method.getName()/*result.getMethod().getMethodName()*/);
        return data.stream().map(RemovingOneOfTwoProductFromBasket::of).toArray();
    }
}
