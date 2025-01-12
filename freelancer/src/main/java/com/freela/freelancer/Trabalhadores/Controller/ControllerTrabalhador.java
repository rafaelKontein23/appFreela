package com.freela.freelancer.Trabalhadores.Controller;


import com.freela.freelancer.Trabalhadores.Etity.TrabalhadorEntidade;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trabalhador")
public class ControllerTrabalhador {

    @PostMapping("/cadastra")
    @Tag(description = "cadastra um trabalhador", name = "cadastraTrabalhador")
    public ResponseEntity<String> cadastraTrabalhador(@Valid @RequestBody TrabalhadorEntidade trabalhador){

        System.out.println(trabalhador);
        return ResponseEntity.ok(trabalhador.toString());
    }



}
