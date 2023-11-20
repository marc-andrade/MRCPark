package com.example.mrcpark.Controller;

import com.example.mrcpark.model.Veiculo;
import com.example.mrcpark.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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
    public ResponseEntity<List<Veiculo>> findAllPaged(){
        return ResponseEntity.ok().body(veiculoService.findAll());
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
