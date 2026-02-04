package com.freela.freelancer.application.address;

import com.freela.freelancer.presentation.address.dto.CepDTO;
import com.freela.freelancer.presentation.share.ResponseDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServicesCep {

    private final String URL_BASE_CEP = "https://viacep.com.br/ws/";
    private final String MASSAGE_ERROR_CEP = "Erro ao buscar Cep";
    private final int VALID_ERROR = 8;

    private final WebClient webClient;

    public ServicesCep(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(URL_BASE_CEP).build();
    }

    public ResponseDefault findCepServices(String cep) {
        if (cep.length() < VALID_ERROR) {
            return new ResponseDefault(false, MASSAGE_ERROR_CEP, null);
        }
        var result = webClient.get().
                uri("/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(CepDTO.class)
                .block();

        boolean valid = result != null && result.getCep() != null;
        return new ResponseDefault(valid, valid ? null : MASSAGE_ERROR_CEP, result);
    }
}
