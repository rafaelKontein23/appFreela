package com.freela.freelancer.cidades.Services;


import com.freela.freelancer.Constantantes.Urls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class  ServiceCidade{


    private final WebClient webClient;

    public ServiceCidade(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Urls.urlBaseIbge).build();
    }


    public List<String> getMunicipiosPorEstado(String uf){
        var teste = webClient.get()
                .uri("/estados/{uf}/municipios", uf)
                .retrieve()
                .bodyToFlux(String.class)
                .collectList()
                .block(); // Bloqueia a thread (pode ser removido se for chamado de forma reativa)

        return teste;

    }

}
