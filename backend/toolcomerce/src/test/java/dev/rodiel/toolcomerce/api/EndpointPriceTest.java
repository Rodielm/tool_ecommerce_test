package dev.rodiel.toolcomerce.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.rodiel.toolcomerce.model.price.PriceResponse;
import dev.rodiel.toolcomerce.utils.Utils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointPriceTest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        ObjectMapper mapper;

        @Test
        public void endpointTest1() throws JsonMappingException, JsonProcessingException {
                String url = "http://localhost:" + port
                                + "/api/v1/prices/search?appDate=202006141000&productId=35455&brandId=1";

                ResponseEntity<String> responseEntity = requestSearch(url);
                PriceResponse priceResp = mapper.readValue(responseEntity.getBody(), PriceResponse.class);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(35455, priceResp.getProductId());
                assertEquals(1, priceResp.getBrandId());
                assertEquals(true, Utils.isDateBetween(priceResp.getAppDate(), priceResp.getStartDate(),
                                priceResp.getEndDate()));
        }

        @Test
        public void endpointTest2() throws JsonMappingException, JsonProcessingException {
                String url = "http://localhost:" + port
                                + "/api/v1/prices/search?appDate=202006141600&productId=35455&brandId=1";
                ResponseEntity<String> responseEntity = requestSearch(url);
                PriceResponse priceResp = mapper.readValue(responseEntity.getBody(), PriceResponse.class);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(35455, priceResp.getProductId());
                assertEquals(1, priceResp.getBrandId());
                assertEquals(true, Utils.isDateBetween(priceResp.getAppDate(), priceResp.getStartDate(),
                                priceResp.getEndDate()));
        }

        @Test
        public void endpointTest3() throws JsonMappingException, JsonProcessingException {
                String url = "http://localhost:" + port
                                + "/api/v1/prices/search?appDate=202006142100&productId=35455&brandId=1";
                ResponseEntity<String> responseEntity = requestSearch(url);
                PriceResponse priceResp = mapper.readValue(responseEntity.getBody(), PriceResponse.class);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(35455, priceResp.getProductId());
                assertEquals(1, priceResp.getBrandId());
                assertEquals(true, Utils.isDateBetween(priceResp.getAppDate(), priceResp.getStartDate(),
                                priceResp.getEndDate()));
        }

        @Test
        public void endpointTest4() throws JsonMappingException, JsonProcessingException {
                String url = "http://localhost:" + port
                                + "/api/v1/prices/search?appDate=202006151000&productId=35455&brandId=1";
                ResponseEntity<String> responseEntity = requestSearch(url);
                PriceResponse priceResp = mapper.readValue(responseEntity.getBody(), PriceResponse.class);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(35455, priceResp.getProductId());
                assertEquals(1, priceResp.getBrandId());
                assertEquals(true, Utils.isDateBetween(priceResp.getAppDate(), priceResp.getStartDate(),
                                priceResp.getEndDate()));
        }

        @Test
        public void endpointTest5() throws JsonMappingException, JsonProcessingException {
                String url = "http://localhost:" + port
                                + "/api/v1/prices/search?appDate=202006162100&productId=35455&brandId=1";
                ResponseEntity<String> responseEntity = requestSearch(url);
                PriceResponse priceResp = mapper.readValue(responseEntity.getBody(), PriceResponse.class);

                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                assertEquals(35455, priceResp.getProductId());
                assertEquals(1, priceResp.getBrandId());
                assertEquals(true, Utils.isDateBetween(priceResp.getAppDate(), priceResp.getStartDate(),
                                priceResp.getEndDate()));
        }

        public ResponseEntity<String> requestSearch(String url) {
                return restTemplate.getForEntity(url, String.class);
        }
}
