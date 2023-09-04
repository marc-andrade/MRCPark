package com.example.mrcpark.repositories;

import com.example.mrcpark.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
