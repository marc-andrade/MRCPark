package com.example.mrcpark.Controller;

import com.example.mrcpark.model.Carro;
import com.example.mrcpark.service.CarroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @GetMapping("/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(carroService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Carro> insert(@RequestBody Carro entity) {
        Carro newCar = carroService.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id")
                .buildAndExpand(newCar.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping
    public ResponseEntity<Page<Carro>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok().body(carroService.findAllPaged(pageable));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@PathVariable Long id, @RequestBody @Valid Carro entity){
        return ResponseEntity.ok().body(carroService.update(id,entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
