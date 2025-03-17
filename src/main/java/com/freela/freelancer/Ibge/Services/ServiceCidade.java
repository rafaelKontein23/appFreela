package com.freela.freelancer.Ibge.Services;


import com.freela.freelancer.Constantantes.Urls;
import com.freela.freelancer.Ibge.DTO.MunicipioDTO;
import com.freela.freelancer.Ibge.Execoes.ExceptionCidade;
import com.freela.freelancer.Ultis.Constantes;
import com.freela.freelancer.Ultis.RespostaPadrao;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ServiceCidade {


    private final WebClient webClient;


    public ServiceCidade(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Urls.urlBaseIbge).build();
    }


    public RespostaPadrao getMunicipiosPorEstado(String uf) {

        if(uf.length() != 2){
            return  new RespostaPadrao(false, null, Constantes.erroCidades);
        }

        var resultado = webClient.get()
                .uri("/estados/{uf}/municipios", uf)
                .retrieve()
                .bodyToFlux(MunicipioDTO.class)
                .collectList()
                .block();


        var valido = resultado != null && !resultado.isEmpty();


        return new RespostaPadrao(valido, valido ? resultado : null, valido ? "" : Constantes.erroCidades);

    }

}
