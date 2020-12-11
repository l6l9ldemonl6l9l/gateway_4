package com.example.gateway.rest;

import com.example.gateway.rest.dto.FairyAnimal;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/FairyAnimal")
@NoArgsConstructor
public class FairyAnimalController {

    private final String url = "http://example-fairyAnimals:8082/fairyAnimal";

    @PostMapping
    public FairyAnimal createFairyAnimal(@RequestBody FairyAnimal FairyAnimal) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FairyAnimal> result =
                restTemplate.postForEntity(url, FairyAnimal, FairyAnimal.class);
        return result.getBody();
    }

    @GetMapping
    public List<FairyAnimal> getAllFairyAnimals() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FairyAnimal>> rateResponse =
                restTemplate.exchange("http://example-FairyAnimals:8082/FairyAnimal",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<FairyAnimal>>() {
                        });
        List<FairyAnimal> FairyAnimal = rateResponse.getBody();
        return FairyAnimal;
    }

    @GetMapping("{id}")
    public FairyAnimal getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FairyAnimal> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }
    
}
