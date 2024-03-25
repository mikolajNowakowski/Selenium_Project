package model.customer.data.order_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import model.customer.Customer;

import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class OrderCompleteTestData {
    private Customer customer;
    private String productName;
    private String promoCode;

    public static OrderCompleteTestData of(List<String> data) {
        return new OrderCompleteTestData(Customer.ofRowData(data), data.get(20), data.get(21));
    }

    @Override
    public String toString() {
        return "OrderCompleteTestData -> [" +
                customer.toString() +
                "\nproductName='" + productName +
                "\npromoCode='" + promoCode + "\n]";
    }
}
