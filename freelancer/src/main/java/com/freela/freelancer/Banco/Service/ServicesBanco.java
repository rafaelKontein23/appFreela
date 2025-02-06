package com.freela.freelancer.Banco.Service;

import com.freela.freelancer.Banco.DTO.BancoDTO;
import com.freela.freelancer.Constantantes.Urls;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ServicesBanco {


    private WebClient webClient;

    public ServicesBanco(WebClient.Builder webClientBuilder){
        webClientBuilder.baseUrl(Urls.urlbrasilApi);
    }

    public List<BancoDTO> buscaBanco(){
        var resultado = webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(BancoDTO.class)
                .collectList()
                .block();

        return resultado;

    }

}
