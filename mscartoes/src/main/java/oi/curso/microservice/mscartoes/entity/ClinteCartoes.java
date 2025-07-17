package oi.curso.microservice.mscartoes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ClinteCartoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_cartoes")
    private Cartoes cartoes;
    private BigDecimal limiteLiberado;

}
