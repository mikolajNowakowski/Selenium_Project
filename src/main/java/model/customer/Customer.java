package model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.customer.payment_method.PaymentMethod;
import model.customer.social_title.SocialTitle;

import java.util.Arrays;
import java.util.List;

@Data
//@AllArgsConstructor
@Builder
public class Customer {

    // ==== Personal info form ====
    private SocialTitle socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private boolean offersFromPartnersEnabled;
    private boolean newsletterSingUp;
    private boolean termsAndConditionsAcceptation;

    // ==== AddressInfoForm ====
    private String company;
    private String address;
    private String addressComplement;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;
    private boolean usageInputtedAddressAsInvoice;

    // ==== Shipping method form ====
    private String shippingComment;

    // ==== Payment method ====
    private PaymentMethod paymentMethod;

    public static Customer ofRowData(List<String> data){

        return Customer.builder()
                .socialTitle(SocialTitle.valueOf(data.get(0)))
                .firstName(data.get(1))
                .lastName(data.get(2))
                .email(data.get(3))
                .password(data.get(4))
                .birthday(data.get(5))
                .offersFromPartnersEnabled(Boolean.valueOf(data.get(6)))
                .newsletterSingUp(Boolean.valueOf(data.get(7)))
                .termsAndConditionsAcceptation(Boolean.valueOf(data.get(8)))
                .company(data.get(9))
                .address(data.get(10))
                .addressComplement(data.get(11))
                .city(data.get(12))
                .state(data.get(13))
                .postalCode(data.get(14))
                .country(data.get(15))
                .phone(data.get(16))
                .usageInputtedAddressAsInvoice(Boolean.valueOf(data.get(17)))
                .shippingComment(data.get(18))
                .paymentMethod(PaymentMethod.valueOf((data.get(19))))
                .build();
    }

    @Override
    public String toString() {
        return "Customer -> [" +
                "\nsocialTitle=" + socialTitle +
                "\nfirstName='" + firstName +
                "\nlastName='" + lastName +
                "\nemail='" + email +
                "\npassword='" + password +
                "\nbirthday='" + birthday +
                "\noffersFromPartnersEnabled=" + offersFromPartnersEnabled +
                "\nnewsletterSingUp=" + newsletterSingUp +
                "\ntermsAndConditionsAcceptation=" + termsAndConditionsAcceptation +
                "\ncompany='" + company +
                "\naddress='" + address +
                "\naddressComplement='" + addressComplement +
                "\ncity='" + city +
                "\nstate='" + state +
                "\npostalCode='" + postalCode +
                "\ncountry='" + country +
                "\nphone='" + phone +
                "\nusageInputtedAddressAsInvoice=" + usageInputtedAddressAsInvoice +
                "\nshippingComment='" + shippingComment +
                "\npaymentMethod=" + paymentMethod + "]";

    }
}
