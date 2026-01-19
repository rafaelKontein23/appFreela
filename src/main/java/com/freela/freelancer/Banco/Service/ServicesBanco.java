package com.freela.freelancer.Banco.Service;

import com.freela.freelancer.presentation.bank.dto.BancoDTO;
import com.freela.freelancer.Constantantes.Urls;
import com.freela.freelancer.Ultis.RespostaPadrao;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ServicesBanco {


    private WebClient webClient;

    public ServicesBanco(WebClient.Builder webClientBuilder){
       this.webClient = webClientBuilder.baseUrl(Urls.urlbrasilApi).build();
    }

    public RespostaPadrao buscaBanco(){
        var resultado = webClient.get()
                .uri("/banks/v1")
                .retrieve()
                .bodyToFlux(BancoDTO.class)
                .collectList()
                .block();
        var valido = resultado != null && !resultado.isEmpty();
        return new RespostaPadrao(valido, valido ? resultado : null, valido ? "" : "Erro ao buscar bancos");

    }

}
