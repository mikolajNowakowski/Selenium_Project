package model.customer.data.removing_product_from_basket;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemovingOneOfTwoProductFromBasket {
    private String firstProductName;
    private String removedProductName;
    private String expectedPrice;

    public static RemovingOneOfTwoProductFromBasket of(Object[] data){
        return new RemovingOneOfTwoProductFromBasket(data[0].toString(),data[1].toString(),data[2].toString());
    }
}
