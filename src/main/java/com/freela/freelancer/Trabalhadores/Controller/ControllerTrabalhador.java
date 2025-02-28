package com.freela.freelancer.Trabalhadores.Controller;


import com.freela.freelancer.Trabalhadores.DTO.LoginDTO;
import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import com.freela.freelancer.Trabalhadores.Services.TrabalhadorUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trabalhador")
public class ControllerTrabalhador {

    @Autowired
    private TrabalhadorUseCase controllerTrabalhadorUseCase;


    @PostMapping("/cadastra")
    @Tag(description = "cadastra um trabalhador", name = "cadastraTrabalhador")
    public ResponseEntity<String> cadastraTrabalhador(@Valid @RequestBody TrabalhadorEntidade trabalhador){
        try {
            controllerTrabalhadorUseCase.salvaCadastroTrabalhador(trabalhador);
            return ResponseEntity.ok("Cadastro realizado com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login/{id}")
    @Tag(description = "login um trabalhador", name = "loginTrabalhador")
    public ResponseEntity<Object> loginTrabalhador(@Valid @RequestBody LoginDTO trabalhador){
        try {
            var result = controllerTrabalhadorUseCase.logaCanditado(trabalhador);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}