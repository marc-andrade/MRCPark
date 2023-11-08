package com.example.mrcpark.service;

import com.example.mrcpark.model.VagaEstacionamento;
import com.example.mrcpark.repositories.VagaEstacionamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VagaEstacionamentoService {


    private final VagaEstacionamentoRepository vagaEstacionamentoRepository;

    @Transactional
    public VagaEstacionamento findById(Long id) {
        return vagaEstacionamentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public VagaEstacionamento insert(VagaEstacionamento entity) {
        if (entity.getOcupada() == null) {
            entity.setOcupada(false);
        }
        return vagaEstacionamentoRepository.save(entity);
    }
    @Transactional
    public Page<VagaEstacionamento> findAllPaged(Pageable pageable) {
        return vagaEstacionamentoRepository.findAll(pageable);
    }

    @Transactional
    public VagaEstacionamento update(Long id, VagaEstacionamento dto) {
        try {
            findById(id);
            return vagaEstacionamentoRepository.save(dto);

        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(Long id) {

        try {
            vagaEstacionamentoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }
}
