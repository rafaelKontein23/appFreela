package com.freela.freelancer.Trabalhadores.Controller;


import com.freela.freelancer.Trabalhadores.DTO.LoginDTO;
import com.freela.freelancer.Trabalhadores.DTO.ProfissaoRequestDTO;
import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import com.freela.freelancer.Trabalhadores.Services.ProfissaoUseCase;
import com.freela.freelancer.Trabalhadores.Services.TrabalhadorUseCase;
import com.freela.freelancer.Ultis.RespostaPadrao;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trabalhador")
@Tag(description = "cadastra um trabalhador", name = "cadastraTrabalhador")

public class ControllerTrabalhador {

    @Autowired
    private TrabalhadorUseCase controllerTrabalhadorUseCase;

    @Autowired
    private ProfissaoUseCase profissaoUseCase;


    @PostMapping("/cadastra")
    public ResponseEntity<Object> cadastraTrabalhador(@Valid @RequestBody TrabalhadorEntidade trabalhador){
        try {
            var result = controllerTrabalhadorUseCase.salvaCadastroTrabalhador(trabalhador);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar/profissao")
    public ResponseEntity<Object> cadastraProfissao(@Valid @RequestBody ProfissaoRequestDTO profissaoRequestDTO){
        try {
            var result = profissaoUseCase.criarProfissao(profissaoRequestDTO.getNome());
            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            var respostaPadrao = new RespostaPadrao(false, null, e.getMessage());
            return  ResponseEntity.badRequest().body(respostaPadrao);
        }
    }

    @GetMapping("/retorna/profissoes")
    public ResponseEntity<Object> retornaProfissoes(){
        try {
            var result = profissaoUseCase.buscaProfissoes();
            return ResponseEntity.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            var respostaPadrao = new RespostaPadrao(false, null, e.getMessage());
            return  ResponseEntity.badRequest().body(respostaPadrao);
        }
    }

    @PostMapping("/login")
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