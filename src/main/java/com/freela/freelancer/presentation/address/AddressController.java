package com.freela.freelancer.presentation.address;


import com.freela.freelancer.Ibge.Services.ServiceCity;
import com.freela.freelancer.Ibge.Services.ServicesCep;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
@Tag(description =  "Busca as cidades no api da ibge", name = "Endereços")
public class AddressController {

    @Autowired
    private ServiceCity serviceCidade;

    @Autowired
    private ServicesCep servicesCep;


    @PostMapping("/{uf}")
    public ResponseEntity<Object> buscaCidades(@PathVariable String uf){
        try {
            var resultado = serviceCidade.getMunicipiosPorEstado(uf);
            return ResponseEntity.ok(resultado);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{CEP}/json/")
    public ResponseEntity<Object> buscaCep(@PathVariable String CEP){
        try {
            var resultado = servicesCep.bucaCep(CEP);
            return ResponseEntity.ok(resultado);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
