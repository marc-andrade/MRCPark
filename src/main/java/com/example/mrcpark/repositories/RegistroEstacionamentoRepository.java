package com.example.mrcpark.repositories;

import com.example.mrcpark.model.RegistroEstacionamento;
import com.example.mrcpark.model.VagaEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroEstacionamentoRepository extends JpaRepository<RegistroEstacionamento, Long> {
}
