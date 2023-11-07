package com.example.mrcpark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroEstacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private BigDecimal valorCobrado;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private VagaEstacionamento vagaEstacionamento;
}
