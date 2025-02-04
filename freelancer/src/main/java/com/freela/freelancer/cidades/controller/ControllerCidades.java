package com.freela.freelancer.cidades.controller;


import com.freela.freelancer.cidades.Services.ServiceCidade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidades")
public class ControllerCidades {

    @Autowired
    private ServiceCidade serviceCidade;


    @PostMapping("/{uf}")
    @Tag(description =  "Busca as cidades no api da ibge", name = "cidades")
    public ResponseEntity<Object> buscaCidades(@PathVariable String uf){
        try {
            var resultado = serviceCidade.getMunicipiosPorEstado(uf);
            return ResponseEntity.ok(resultado);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());

        }
    }


}
