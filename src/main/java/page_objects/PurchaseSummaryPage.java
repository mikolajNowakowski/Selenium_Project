package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchaseSummaryPage {

    private WebDriver driver;

    By orderSuccessInfo = By.xpath("//h3[@class = 'h1 card-title']");
    By maileNotificationInfoTitle = By.xpath("//h3[@class = 'h1 card-title']/following::p[contains(text(),'email has been sent')]");

    public PurchaseSummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderConfirmationInfoDisplayed(){
        return driver.findElement(orderSuccessInfo).isDisplayed();
    }

    public boolean doesMailSentInfoContainsCorrectMail(String email){
        return driver.findElement(maileNotificationInfoTitle).getText().contains(email);
    }
}
