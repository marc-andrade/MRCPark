package com.example.mrcpark.Controller;

import com.example.mrcpark.model.RegistroEstacionamento;
import com.example.mrcpark.service.RegistroEstacionamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registros-estacionamento")
@RequiredArgsConstructor
public class RegistroEstacionamentoController {

    private final RegistroEstacionamentoService registroEstacionamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<RegistroEstacionamento> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(registroEstacionamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RegistroEstacionamento> insert(@RequestBody RegistroEstacionamento entity) {
        RegistroEstacionamento newCar = registroEstacionamentoService.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id")
                .buildAndExpand(newCar.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping
    public ResponseEntity<Page<RegistroEstacionamento>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok().body(registroEstacionamentoService.findAllPaged(pageable));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RegistroEstacionamento> update(@PathVariable Long id, @RequestBody @Valid RegistroEstacionamento entity){
        return ResponseEntity.ok().body(registroEstacionamentoService.update(id,entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        registroEstacionamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/dar-baixa/{id}")
    public ResponseEntity<RegistroEstacionamento> darBaixaEstacionamento(@PathVariable Long id, @RequestBody RegistroEstacionamento baixa){
        return ResponseEntity.ok().body(registroEstacionamentoService.darBaixaEstacionamento(id,baixa));
    }

}
