package dev.rodiel.toolcomerce.model.price;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceParam {

    private Long brandId;
    private Long productId;
    private LocalDateTime appDate;
}
