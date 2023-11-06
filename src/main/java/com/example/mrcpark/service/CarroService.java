package com.example.mrcpark.service;

import com.example.mrcpark.model.Carro;
import com.example.mrcpark.repositories.CarroRepository;
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
public class CarroService {

    private final CarroRepository carroRepository;


    @Transactional
    public Carro findById(Long id) {
        return carroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public Carro insert(Carro entity) {
        return carroRepository.save(entity);
    }
    @Transactional
    public Page<Carro> findAllPaged(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    @Transactional
    public Carro update(Long id, Carro dto) {
        try {
            carroRepository.getReferenceById(id);
            return carroRepository.save(dto);

        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(Long id) {

        try {
            carroRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }
}
