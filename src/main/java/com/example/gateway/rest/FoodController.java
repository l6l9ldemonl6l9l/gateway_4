package com.example.gateway.rest;

import com.example.gateway.rest.dto.Food;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Food")
@NoArgsConstructor
public class FoodController {

    private final String url = "http://example-foods:8081/food";

    @PostMapping
    public Food createFood(@RequestBody Food Food) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Food> result =
                restTemplate.postForEntity(url, Food, Food.class);
        return result.getBody();
    }

    @GetMapping
    public List<Food> getAllFoods() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Food>> rateResponse =
                restTemplate.exchange("http://example-Foods:8081/Food",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Food>>() {
                        });
        List<Food> Food = rateResponse.getBody();
        return Food;
    }

    @GetMapping("{id}")
    public Food getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Food> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }
    
}
