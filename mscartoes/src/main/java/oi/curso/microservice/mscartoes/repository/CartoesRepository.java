package oi.curso.microservice.mscartoes.repository;

import oi.curso.microservice.mscartoes.entity.Cartoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartoesRepository extends JpaRepository<Cartoes, Long> {

    List<Cartoes> findByRendaLessThanEqual(Long renda);
}
