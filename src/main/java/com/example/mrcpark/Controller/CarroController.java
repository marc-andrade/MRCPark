package com.example.mrcpark.Controller;

import com.example.mrcpark.model.Carro;
import com.example.mrcpark.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<Carro> insert(@RequestBody Carro entity) {
        Carro newCar = carroService.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id")
                .buildAndExpand(newCar.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

}
