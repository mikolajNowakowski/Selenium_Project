package page_objects;

import model.customer.Customer;
import model.customer.payment_method.PaymentMethod;
import model.customer.social_title.SocialTitle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;

    By mrRadioButton = By.cssSelector(".col-md-6.form-control-valign > label:nth-of-type(1)");
    By mrsRadioButton = By.cssSelector(".col-md-6.form-control-valign > label:nth-of-type(2)");
    By firstNameInput = By.name("firstname");
    By lastNameInput = By.name("lastname");
    By emailInput = By.name("email");
    By agreementOfConditionBox = By.name("psgdpr");
    By companyNameInput = By.name("company");
    By addressInput = By.name("address1");
    By additionalAddressInput = By.name("address2");
    By cityInput = By.name("city");
    By stateSelect = By.name("id_state");
    By postalCodeInput = By.name("postcode");
    By countrySelect = By.name("id_country");
    By phone = By.name("phone");
    By shippingMethodCommentInput = By.cssSelector("textarea#delivery_message");
    By payByCheckRadio = By.id("payment-option-1");
    By payByBankWireRadio = By.id("payment-option-2");
    By termsOfServiceAgreementCheckBox = By.name("conditions_to_approve[terms-and-conditions]");
    By personalInfoFormContinueButton = By.name("continue");
    By addressInfoConfirmationButton = By.name("confirm-addresses");
    By deliveryMethodConfirmationButton = By.name("confirmDeliveryOption");
    By orderConfirmationButton = By.cssSelector(".btn.btn-primary.center-block");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage setSocialTitle(SocialTitle socialTitle) {
        if (socialTitle.equals(SocialTitle.MR))
            driver.findElement(mrRadioButton).click();
        else {
            driver.findElement(mrsRadioButton).click();
        }
        return this;
    }

    public CheckoutPage provideFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        return this;
    }

    public CheckoutPage provideLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
        return this;
    }

    public CheckoutPage provideEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }

    public CheckoutPage toggleTermsAndConditions() {
        driver.findElement(agreementOfConditionBox).click();
        return this;
    }

    public CheckoutPage confirmPersonalInformation() {
        driver.findElement(personalInfoFormContinueButton).click();
        return this;
    }

    public CheckoutPage provideCompany(String companyName) {
        driver.findElement(companyNameInput).sendKeys(companyName);
        return this;
    }

    public CheckoutPage provideAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
        return this;
    }

    public CheckoutPage provideAddressComplement(String address) {
        driver.findElement(additionalAddressInput).sendKeys(address);
        return this;
    }

    public CheckoutPage provideCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
        return this;
    }

    public CheckoutPage chooseState(String state) {
        var select = new Select(driver.findElement(stateSelect));
        select.selectByVisibleText(state);
        return this;
    }

    public CheckoutPage providePostalCode(String postalCode) {
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        return this;
    }

    public CheckoutPage selectCountry(String country) {
        var select = new Select(driver.findElement(countrySelect));
        select.selectByVisibleText(country);
        return this;
    }

    public CheckoutPage providePhoneNumber(String phoneNumber) {
        driver.findElement(phone).sendKeys(phoneNumber);
        return this;
    }

    public CheckoutPage confirmAddressInfo() {
        driver.findElement(addressInfoConfirmationButton).click();
        return this;
    }

    public CheckoutPage provideShippingComment(String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(shippingMethodCommentInput)).sendKeys(comment);
        return this;
    }

    public CheckoutPage confirmShippingMethod() {
        driver.findElement(deliveryMethodConfirmationButton).click();
        return this;
    }


    public CheckoutPage setPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethod == PaymentMethod.CHECK ? setPaymentByCheck() : setPaymentByBankWire();
    }


    public CheckoutPage setPaymentByCheck() {
        driver.findElement(payByCheckRadio).click();
        return this;
    }

    public CheckoutPage setPaymentByBankWire() {
        driver.findElement(payByBankWireRadio).click();
        return this;
    }

    public CheckoutPage toggleToS() {
        driver.findElement(termsOfServiceAgreementCheckBox).click();
        return this;
    }

    public PurchaseSummaryPage confirmOrder() {
        driver.findElement(orderConfirmationButton).click();
        return new PurchaseSummaryPage(driver);
    }


    public PurchaseSummaryPage completeCheckoutForm(Customer customer) {
        return setSocialTitle(customer.getSocialTitle())
                .provideFirstName(customer.getFirstName())
                .provideLastName(customer.getLastName())
                .provideEmail(customer.getEmail())
                .toggleTermsAndConditions()
                .confirmPersonalInformation()
                .provideCompany(customer.getCompany())
                .provideAddress(customer.getAddress())
                .provideAddressComplement(customer.getAddressComplement())
                .provideCity(customer.getCity())
                .chooseState(customer.getState())
                .selectCountry(customer.getCountry())
                .providePostalCode(customer.getPostalCode())
                .providePhoneNumber(customer.getPhone())
                .confirmAddressInfo()
                .provideShippingComment(customer.getShippingComment())
                .confirmShippingMethod()
                .setPaymentMethod(customer.getPaymentMethod())
                .toggleToS()
                .confirmOrder();
    }


}
