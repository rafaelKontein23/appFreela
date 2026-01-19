package com.freela.freelancer.application.address;

import com.freela.freelancer.Constantantes.Urls;
import com.freela.freelancer.presentation.address.dto.CepDTO;
import com.freela.freelancer.Ultis.Constantes;
import com.freela.freelancer.Ultis.RespostaPadrao;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServicesCep {

    private WebClient webClient;

    public ServicesCep(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Urls.urlBaseCep).build();
    }

    public RespostaPadrao bucaCep(String cep){
        if(cep.length() < 8){
            return new RespostaPadrao(false, null, Constantes.erroCep);
        }
        var resultado = webClient.get().
                  uri("/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(CepDTO.class)
                .block();

        boolean valido = resultado != null && resultado.getCep() != null;
        return new RespostaPadrao(valido, valido ? resultado : null, valido ? "" : Constantes.erroCep);
    }
}
