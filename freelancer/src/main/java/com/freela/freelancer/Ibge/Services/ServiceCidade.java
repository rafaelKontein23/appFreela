package com.freela.freelancer.Ibge.Services;


import com.freela.freelancer.Constantantes.Urls;
import com.freela.freelancer.Ibge.DTO.MunicipioDTO;
import com.freela.freelancer.Ibge.Execoes.ExceptionCidade;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class  ServiceCidade{


    private final WebClient webClient;


    public ServiceCidade(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Urls.urlBaseIbge).build();
    }


    public List<MunicipioDTO> getMunicipiosPorEstado(String uf){
        var resultado = webClient.get()
                .uri("/estados/{uf}/municipios", uf)
                .retrieve()
                .bodyToFlux(MunicipioDTO.class)
                .collectList()
                .block();

        if(resultado.isEmpty() || resultado == null ){
            throw  new ExceptionCidade();
        }

        return resultado;

    }

}
