package model.customer.data.removing_product_from_basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class RemovingOneOfTwoProductFromBasket {
    private String firstProductName;
    private String removedProductName;
    private String expectedPrice;

    public static RemovingOneOfTwoProductFromBasket of(List<String> data){
        return new RemovingOneOfTwoProductFromBasket(data.get(0),data.get(1),data.get(2));
    }


}
