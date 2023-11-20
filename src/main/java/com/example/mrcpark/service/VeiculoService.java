package com.example.mrcpark.service;

import com.example.mrcpark.model.Veiculo;
import com.example.mrcpark.repositories.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Transactional
    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public Veiculo insert(Veiculo entity) {
        return veiculoRepository.save(entity);
    }
//    @Transactional
//    public Page<Veiculo> findAllPaged(Pageable pageable) {
//        return veiculoRepository.findAll(pageable);
//    }

    @Transactional
    public Veiculo update(Long id, Veiculo dto) {
        try {
            findById(id);
            return veiculoRepository.save(dto);

        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(Long id) {

        try {
            veiculoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }
    @Transactional
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }
}
