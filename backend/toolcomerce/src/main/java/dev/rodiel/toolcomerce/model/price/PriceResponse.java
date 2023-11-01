package dev.rodiel.toolcomerce.model.price;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceResponse {

    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime appDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double finalPrice;
    private String curr;
}
