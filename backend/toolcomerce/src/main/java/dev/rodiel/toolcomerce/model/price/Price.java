package dev.rodiel.toolcomerce.model.price;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PRICES")
public class Price implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long id;
    @Column(name = "brand_id")
    private Long brand;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Long priceList;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "price")
    private Double price;
    @Column(name = "curr")
    private String curr;

}
