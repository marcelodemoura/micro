package oi.curso.microservice.mscartoes.repository;


import oi.curso.microservice.mscartoes.entity.ClinteCartoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartoesRepository extends JpaRepository<ClinteCartoes, Long> {

    List<ClinteCartoes> findByCpf(String cpf);



}
