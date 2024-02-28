package model.customer.data.order_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import model.customer.Customer;

@AllArgsConstructor
@Data
@ToString
public class OrderCompleteTestData {
    private Customer customer;
    private String productName;
    private String promoCode;

    public static OrderCompleteTestData of(Object[] data) {
        return new OrderCompleteTestData(Customer.ofRowData(data), data[20].toString(), data[21].toString());
    }

    @Override
    public String toString() {
        return "OrderCompleteTestData -> [" +
                customer.toString() +
                "\nproductName='" + productName +
                "\npromoCode='" + promoCode + "\n]";
    }
}
