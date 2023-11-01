package dev.rodiel.toolcomerce.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.rodiel.toolcomerce.model.price.Price;
import dev.rodiel.toolcomerce.model.price.PriceEntry;
import dev.rodiel.toolcomerce.model.price.PriceParam;
import dev.rodiel.toolcomerce.model.price.PriceResponse;
import dev.rodiel.toolcomerce.service.PriceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/prices")
public class PriceCtrl {

    private static final String DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";

    @Autowired
    private PriceService priceService;

    @GetMapping("/original")
    public ResponseEntity<List<Price>> getAllPrices() {
        return new ResponseEntity<List<Price>>(priceService.getAll(),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getAll() {
        return new ResponseEntity<List<PriceResponse>>(priceService.allPrice(),
                HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam Long brandId,
            @RequestParam Long productId,
            @RequestParam String appDate) {

        if (appDate.length() < DATE_FORMAT_PATTERN.length()) {
            appDate = appDate + "00";
        }

        // convert string to localDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        LocalDateTime dateTime = LocalDateTime.parse(appDate, formatter);

        // set priceParam
        PriceParam priceParam = new PriceParam(brandId, productId, dateTime);
        PriceResponse priceResponse = priceService.getPrice(priceParam);

        if (priceResponse != null) {
            return new ResponseEntity<PriceResponse>(priceResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<PriceResponse>(priceResponse, HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<Price> createPrice(@RequestBody PriceEntry priceEntry) {
        Price price = priceService.createPrice(priceEntry);

        // DateTimeFormatter formatter =
        // DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        // LocalDateTime startDateFormatted = LocalDateTime
        // .parse(Utils.fixMissingHours(priceEntry.getStartDate(), DATE_FORMAT_PATTERN),
        // formatter);
        // LocalDateTime endDateFormatted =
        // LocalDateTime.parse(Utils.fixMissingHours(priceEntry.getEnDate(),
        // DATE_FORMAT_PATTERN), formatter);

        // return ResponseEntity.ok("PriceEntry created successfully");
        if (priceEntry != null) {
            return new ResponseEntity<Price>(price, HttpStatus.OK);
        } else {
            return new ResponseEntity<Price>(HttpStatus.NO_CONTENT);
        }
    }

}