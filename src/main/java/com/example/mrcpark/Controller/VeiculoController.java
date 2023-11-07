package com.example.mrcpark.Controller;

import com.example.mrcpark.model.Veiculo;
import com.example.mrcpark.service.VeiculoService;
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
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(veiculoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Veiculo> insert(@RequestBody Veiculo entity) {
        Veiculo newCar = veiculoService.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id")
                .buildAndExpand(newCar.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping
    public ResponseEntity<Page<Veiculo>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok().body(veiculoService.findAllPaged(pageable));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable Long id, @RequestBody @Valid Veiculo entity){
        return ResponseEntity.ok().body(veiculoService.update(id,entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
