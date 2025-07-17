package oi.curso.microservice.mscartoes.service;

import lombok.RequiredArgsConstructor;
import oi.curso.microservice.mscartoes.entity.ClinteCartoes;
import oi.curso.microservice.mscartoes.repository.ClienteCartoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartoesService {

    private final ClienteCartoesRepository clienteCartoesRepository;

//    @Transactional
//    public Cartoes save(ClineteCartoes cartoes) {
//        return clienteCartoesRepository.save(ClineteCartoes);
//    }

    public List<ClinteCartoes> buscarPorCpf(String cpf) {
        return clienteCartoesRepository.findByCpf(cpf);
    }

}
