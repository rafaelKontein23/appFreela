package com.freela.freelancer.Trabalhadores.Usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.freela.freelancer.Trabalhadores.DTO.LoginDTO;
import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import com.freela.freelancer.Trabalhadores.Repository.TrabalhadorRepository;
import com.freela.freelancer.Trabalhadores.execoes.LoginTrabalhadorExepiton;
import com.freela.freelancer.Trabalhadores.execoes.TrablhadorExecoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class TrabalhadorUseCase {
    @Value("${security.token.secret}")
    private String chaveSecreta ;

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void salvaCadastroTrabalhador(TrabalhadorEntidade trabalhadorEntidade){
        trabalhadorRepository.findByCpf(trabalhadorEntidade.getCpf())
                .ifPresent(usuario -> {
                    throw new TrablhadorExecoes();
                });

        var senhaEncode= passwordEncoder.encode(trabalhadorEntidade.getSenha());
        trabalhadorEntidade.setSenha(senhaEncode);

        trabalhadorRepository.save(trabalhadorEntidade);
    }

    public void logaCanditado(LoginDTO loginDTO){
        var usuario = trabalhadorRepository.findByCpf(loginDTO.getCpf()).orElseThrow(() -> {
            throw new LoginTrabalhadorExepiton("Usuario ou sneha incorretos");
        } );
        var senhaEncote  = passwordEncoder.encode( loginDTO.getSenha());
        var senhaVerdade = senhaEncote.equals( usuario.getSenha());
        if(!senhaVerdade){
            throw new LoginTrabalhadorExepiton("Usuario ou sneha incorretos");
        }
        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
        var token = JWT.create().withIssuer("NomeDAempresa").withExpiresAt(Instant.now().plus(Duration.ofHours(2))) // pra colocar um tempo de duração para o token
                .withClaim("roles", Arrays.asList("empresa"))
                .withSubject(usuario.getId().toString()).sign(algorithm);
        // continuar aqui, fazer o o objeto de retotno do login

    }

}
