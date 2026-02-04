package com.freela.freelancer.presentation.address.controller;


import com.freela.freelancer.application.address.ServiceCity;
import com.freela.freelancer.application.address.ServicesCep;
import com.freela.freelancer.presentation.share.ResponseDefault;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
@Tag(description =  "Busca as cidades no api da ibge", name = "Endereços")
public class AddressController {

    @Autowired
    private ServiceCity serviceCity;

    @Autowired
    private ServicesCep servicesCep;


    @PostMapping("/{uf}")
    public ResponseEntity<Object> findCity(@PathVariable String uf){
        try {
            ResponseDefault result = serviceCity.getMunicipiosPorEstado(uf);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{CEP}/json/")
    public ResponseEntity<Object> finCep(@PathVariable String CEP){
        try {
            ResponseDefault result = servicesCep.findCepServices(CEP);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
