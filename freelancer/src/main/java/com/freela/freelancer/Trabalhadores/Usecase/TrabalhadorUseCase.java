package com.freela.freelancer.Trabalhadores.Usecase;

import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import com.freela.freelancer.Trabalhadores.Repository.TrabalhadorRepository;
import com.freela.freelancer.Trabalhadores.execoes.TrablhadorExecoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabalhadorUseCase {


    @Autowired
    private TrabalhadorRepository trabalhadorRepository;


    public void salvaCadastroTrabalhador(TrabalhadorEntidade trabalhadorEntidade){
        trabalhadorRepository.findByCpf(trabalhadorEntidade.getCpf())
                .ifPresent(usuario -> {
                    throw new TrablhadorExecoes();
                });

        trabalhadorRepository.save(trabalhadorEntidade);
    }

}
