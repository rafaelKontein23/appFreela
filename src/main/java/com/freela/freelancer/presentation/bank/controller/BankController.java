package com.freela.freelancer.presentation.bank.controller;

import com.freela.freelancer.application.bank.ServicesBanco;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco")
public class BankController {

    @Autowired
    private ServicesBanco serviceBanco;


    @GetMapping("/buscaBanco")
    @Tag(name = "banco", description = "Busca os bancoo na api brasilapi")
    public ResponseEntity<Object> buscaBanco(){
        try {
            var resultado = serviceBanco.buscaBanco();
            return ResponseEntity.ok(resultado);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
