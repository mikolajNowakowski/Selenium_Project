package model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.customer.payment_method.PaymentMethod;
import model.customer.social_title.SocialTitle;

import java.util.Arrays;

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

    public static Customer ofRowData(Object[] data){

        return Customer.builder()
                .socialTitle(SocialTitle.valueOf(data[0].toString()))
                .firstName(data[1].toString())
                .lastName(data[2].toString())
                .email(data[3].toString())
                .password(data[4].toString())
                .birthday(data[5].toString())
                .offersFromPartnersEnabled(Boolean.valueOf(data[6].toString()))
                .newsletterSingUp(Boolean.valueOf(data[7].toString()))
                .termsAndConditionsAcceptation(Boolean.valueOf(data[8].toString()))
                .company(data[9].toString())
                .address(data[10].toString())
                .addressComplement(data[11].toString())
                .city(data[12].toString())
                .state(data[13].toString())
                .postalCode(data[14].toString())
                .country(data[15].toString())
                .phone(data[16].toString())
                .usageInputtedAddressAsInvoice(Boolean.valueOf(data[17].toString()))
                .shippingComment(data[18].toString())
                .paymentMethod(PaymentMethod.valueOf((data[19].toString())))
                .build();
    }
}
