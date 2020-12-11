package com.example.gateway.rest;

import com.example.gateway.rest.dto.Terrain;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Terrain")
@NoArgsConstructor
public class TerrainController {

    private final String url = "http://example-terrains:8084/terrain";

    @PostMapping
    public Terrain createTerrain(@RequestBody Terrain Terrain) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Terrain> result =
                restTemplate.postForEntity(url, Terrain, Terrain.class);
        return result.getBody();
    }

    @GetMapping
    public List<Terrain> getAllTerrains() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Terrain>> rateResponse =
                restTemplate.exchange("http://example-Terrains:8084/Terrain",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Terrain>>() {
                        });
        List<Terrain> Terrain = rateResponse.getBody();
        return Terrain;
    }

    @GetMapping("{id}")
    public Terrain getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Terrain> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }
    
}
