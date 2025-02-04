package com.freela.freelancer.Trabalhadores.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.freela.freelancer.Trabalhadores.DTO.LoginDTO;
import com.freela.freelancer.Trabalhadores.DTO.RespostaLoginDTO;
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

    public RespostaLoginDTO logaCanditado(LoginDTO loginDTO){
        var usuario = trabalhadorRepository.findByCpf(loginDTO.getCpf()).orElseThrow(() -> {
            throw new LoginTrabalhadorExepiton("Usuario ou sneha incorretos");
        } );

        var senhaVerdade = passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha()); // para verifica se a senha está coreeta

        if(!senhaVerdade){
            throw new LoginTrabalhadorExepiton("Usuario ou sneha incorretos");
        }
        Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
        var token = JWT.create().withIssuer("NomeDAempresa").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("empresa"))
                .withSubject(usuario.getId().toString()).sign(algorithm);

        return new RespostaLoginDTO(token, usuario.getCpf());

    }

}
