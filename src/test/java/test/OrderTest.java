package test;

import model.customer.data.order_test.OrderCompleteTestData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page_objects.HomePage;
import utilities.FileReaders;
import java.lang.reflect.Method;

@Listeners({BaseTest.class})


public class OrderTest extends BaseTest {


    @Test(dataProvider = "orderCompleteTestProvider")
    public void orderCompleteTest(OrderCompleteTestData orderCompleteTestData) {
extentTest.pass("Test data: %s".formatted(orderCompleteTestData.toString()));

        var summaryPage = new HomePage(driver)
                .closeCookieWarning()
                .goToTestStore()
                .clickSpecifiedProduct(orderCompleteTestData.getProductName())
                .increaseQuantityOfProductByButton()
                .clickAddToCartButton()
                .clickGoToCheckoutButton()
                .activatePromoCodeInput()
                .providePromoCode(orderCompleteTestData.getPromoCode())
                .addPromoCode()
                .goToCheckout()
                .completeCheckoutForm(orderCompleteTestData.getCustomer());

        Assert.assertTrue(summaryPage.isOrderConfirmationInfoDisplayed());
        Assert.assertTrue(summaryPage.doesMailSentInfoContainsCorrectMail(orderCompleteTestData.getCustomer().getEmail()));
    }

    @DataProvider
    private Object[] orderCompleteTestProvider(Method method) {
        var props = FileReaders.propertiesLoader(appPropertiesPath);
        var data = FileReaders.loadExcelData(props.getProperty("exelFileName"), method.getName());
        return  data.stream().map(OrderCompleteTestData::of).toArray();
    }
}
