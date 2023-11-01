package dev.rodiel.toolcomerce.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.rodiel.toolcomerce.model.price.Price;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {

        // SELECT * FROM prices as p
        // WHERE p.brand_id = 1 AND p.product_id = 35455
        // AND ('2020-06-14 15:00:00' BETWEEN p.start_date AND p.end_date)
        // ORDER BY p.priority ASC
        @Query("""
                Select p from Price p
                where p.brand = ?1 AND p.productId = ?2 AND (?3 BETWEEN p.startDate AND p.endDate)
                ORDER BY p.priority ASC
                """)
        List<Price> findByBrandProductAndDate(Long brandId, Long productId, LocalDateTime appDate);

        @Query("""
                Select p from Price p
                """)
        List<Price> findAllCustom();
}
