package com.example.mrcpark.service;

import com.example.mrcpark.model.RegistroEstacionamento;
import com.example.mrcpark.model.VagaEstacionamento;
import com.example.mrcpark.repositories.RegistroEstacionamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistroEstacionamentoService {

    private final RegistroEstacionamentoRepository registroEstacionamentoRepository;
    private final VeiculoService veiculoService;
    private final VagaEstacionamentoService vagaEstacionamentoService;

    @Transactional
    public RegistroEstacionamento findById(Long id) {
        return registroEstacionamentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public RegistroEstacionamento insert(RegistroEstacionamento entity) {
        VagaEstacionamento vagaEstacionamento = vagaEstacionamentoService.findById(entity.getId());
        veiculoService.findById(entity.getVeiculo().getId());
        if(vagaEstacionamento.getOcupada()) {
            //TODO criar erro de vaga ocupada
            throw new EntityNotFoundException();
        }
        if(entity.getEntrada() == null) {
            entity.setEntrada(LocalDateTime.now());
        }
        return registroEstacionamentoRepository.save(entity);
    }
    @Transactional
    public Page<RegistroEstacionamento> findAllPaged(Pageable pageable) {
        return registroEstacionamentoRepository.findAll(pageable);
    }

    @Transactional
    public RegistroEstacionamento update(Long id, RegistroEstacionamento entity) {
        try {
            findById(id);
            return registroEstacionamentoRepository.save(entity);

        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(Long id) {

        try {
            registroEstacionamentoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }

    public RegistroEstacionamento darBaixaEstacionamento(Long id, RegistroEstacionamento baixa) {
        return null;
    }
}
