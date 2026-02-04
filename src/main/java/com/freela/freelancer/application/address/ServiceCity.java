package com.freela.freelancer.application.address;

import com.freela.freelancer.presentation.address.dto.MunicipioDTO;
import com.freela.freelancer.presentation.share.ResponseDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServiceCity {

    private final String MESSAGE_ERROR_UF = "Sua uf é invalida";
    private final String URL_BASE_IBGE = "https://servicodados.ibge.gov.br/api/v1";

    private final WebClient webClient;

    public ServiceCity(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(URL_BASE_IBGE).build();
    }

    public ResponseDefault getMunicipiosPorEstado(String uf) {
        if (uf.length() != 2) {
            return new ResponseDefault(false, MESSAGE_ERROR_UF, null);
        }

        var result = webClient.get()
                .uri("/estados/{uf}/municipios", uf)
                .retrieve()
                .bodyToFlux(MunicipioDTO.class)
                .collectList()
                .block();

        var valid = result != null && !result.isEmpty();
        return new ResponseDefault(valid, valid ? null : "", result);
    }
}