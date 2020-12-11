package com.example.gateway.rest;

import com.example.gateway.rest.dto.Deity;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Deity")
@NoArgsConstructor
public class DeityController {

    private final String url = "http://example-deitys:8083/deity";

    @PostMapping
    public Deity createDeity(@RequestBody Deity Deity) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Deity> result =
                restTemplate.postForEntity(url, Deity, Deity.class);
        return result.getBody();
    }

    @GetMapping
    public List<Deity> getAllDeitys() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Deity>> rateResponse =
                restTemplate.exchange("http://example-Deitys:8083/Deity",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Deity>>() {
                        });
        List<Deity> Deity = rateResponse.getBody();
        return Deity;
    }

    @GetMapping("{id}")
    public Deity getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Deity> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }
    
}
