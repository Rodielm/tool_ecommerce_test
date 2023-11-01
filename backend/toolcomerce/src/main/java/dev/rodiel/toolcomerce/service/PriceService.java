package dev.rodiel.toolcomerce.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.rodiel.toolcomerce.model.price.Price;
import dev.rodiel.toolcomerce.model.price.PriceEntry;
import dev.rodiel.toolcomerce.model.price.PriceParam;
import dev.rodiel.toolcomerce.model.price.PriceResponse;
import dev.rodiel.toolcomerce.repo.PriceRepo;
import dev.rodiel.toolcomerce.utils.Utils;

@Service
public class PriceService {

    @Autowired
    private PriceRepo priceRepo;

    public List<Price> getAll() {
        List<Price> prices = priceRepo.findAll();
        return prices;
    }

    public List<PriceResponse> allPrice() {
        List<Price> prices = (List<Price>) priceRepo.findAll();

        List<PriceResponse> priceResp = prices.stream().map(price -> makePriceResponse(null, price))
                .collect(Collectors.toList());

        return priceResp;
    }

    public PriceResponse getPrice(PriceParam priceParam) {
        List<Price> prices = priceRepo.findByBrandProductAndDate(priceParam.getBrandId(),
                priceParam.getProductId(), priceParam.getAppDate());

        PriceResponse priceResponse = null;
        if (!prices.isEmpty()) {
            Price price = prices.stream().max(Comparator.comparing(p -> p.getPriority())).get();
            priceResponse = makePriceResponse(priceParam, price);
        }
        return priceResponse;
    }

    private PriceResponse makePriceResponse(PriceParam priceParam, Price price) {
        PriceResponse priceResponse = new PriceResponse();

        priceResponse.setStartDate(price.getStartDate());
        priceResponse.setEndDate(price.getEndDate());
        priceResponse.setBrandId(price.getBrand());
        priceResponse.setProductId(price.getProductId());
        priceResponse.setPriceList(price.getPriceList());
        priceResponse.setCurr(null != price.getCurr() ? price.getCurr() : "EUR");
        priceResponse.setFinalPrice(null != price.getPrice() ? price.getPrice() + price.getPriceList() : 0);

        if (priceParam != null && null != priceParam.getAppDate()) {
            priceResponse.setAppDate(priceParam.getAppDate());
        }

        return priceResponse;
    }

    public Price createPrice(PriceEntry priceEntry) {
        Price price = new Price();
        price.setBrand(priceEntry.getBrandId());
        price.setProductId(priceEntry.getProductId());
        price.setPriceList(priceEntry.getPriceList());
        price.setPriority(priceEntry.getPriority());
        price.setCurr("EUR");
        price.setPrice(priceEntry.getPrice());
        price.setStartDate(Utils.dateFormatter(priceEntry.getStartDate(), "yyyyMMddHHmmss"));
        price.setEndDate(Utils.dateFormatter(priceEntry.getEndDate(), "yyyyMMddHHmmss"));
        return priceRepo.save(price);

    }
}
