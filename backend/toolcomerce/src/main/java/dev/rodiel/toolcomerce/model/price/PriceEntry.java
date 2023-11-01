package dev.rodiel.toolcomerce.model.price;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceEntry {

    private Long brandId;
    private Long productId;
    private Long priceList;
    private Integer priority;
    private Double price;
    private String startDate;
    private String endDate;

}
