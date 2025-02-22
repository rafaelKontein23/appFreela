package com.freela.freelancer.Ibge.Services;

import com.freela.freelancer.Constantantes.Urls;
import com.freela.freelancer.Ibge.DTO.CepDTO;
import com.freela.freelancer.Ibge.DTO.MunicipioDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServicesCep {

    private WebClient webClient;

    public ServicesCep(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Urls.urlBaseCep).build();
    }

    public CepDTO gerCep(String cep){
        var resultado = webClient.get().
                  uri("/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(CepDTO.class)
                .block();

        return resultado;
    }
}
