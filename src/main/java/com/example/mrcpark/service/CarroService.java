package com.example.mrcpark.service;

import com.example.mrcpark.model.Carro;
import com.example.mrcpark.repositories.CarroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;

    @Transactional
    public Carro insert(Carro entity) {
        return carroRepository.save(entity);
    }
}
