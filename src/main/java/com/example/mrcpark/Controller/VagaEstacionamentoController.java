package com.example.mrcpark.Controller;

import com.example.mrcpark.model.VagaEstacionamento;
import com.example.mrcpark.service.VagaEstacionamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/vagas")
@RequiredArgsConstructor
public class VagaEstacionamentoController {

    private final VagaEstacionamentoService vagaEstacionamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<VagaEstacionamento> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(vagaEstacionamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VagaEstacionamento> insert(@RequestBody VagaEstacionamento entity) {
        VagaEstacionamento newCar = vagaEstacionamentoService.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id")
                .buildAndExpand(newCar.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping
    public ResponseEntity<Page<VagaEstacionamento>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok().body(vagaEstacionamentoService.findAllPaged(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagaEstacionamento> update(@PathVariable Long id, @RequestBody @Valid VagaEstacionamento entity){
        return ResponseEntity.ok().body(vagaEstacionamentoService.update(id,entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        vagaEstacionamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
