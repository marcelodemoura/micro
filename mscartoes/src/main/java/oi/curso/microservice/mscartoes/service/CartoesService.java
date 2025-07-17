package oi.curso.microservice.mscartoes.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import oi.curso.microservice.mscartoes.entity.Cartoes;
import oi.curso.microservice.mscartoes.repository.CartoesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CartoesService {

    private final CartoesRepository cartoesRepository;

//    @Transactional
//    public Cartoes save(Cartoes cartoes){
//        return cartoesRepository.save(cartoes);
//    }
//     public List<Cartoes> rendaMenorIgual(Long renda){
//        var rendaBigDecimal = BigDecimal.valueOf(renda);
//        return cartoesRepository.findByRendaLessThanEqual(rendaBigDecimal);
//     }


    @Transactional
    public Cartoes save(Cartoes cartoes) {
        return cartoesRepository.save(cartoes);
    }

    public List<Cartoes> buscarPorRendaAte(Long renda) {
        return cartoesRepository.findByRendaLessThanEqual(renda);
    }

}
