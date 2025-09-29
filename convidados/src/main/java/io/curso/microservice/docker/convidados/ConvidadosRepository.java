package io.curso.microservice.docker.convidados;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvidadosRepository extends JpaRepository<Convidado, String> {
}
