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
    @Value("${security.token.secret.trabalhador}")
    private  String cahveSecreta;

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void salvaCadastroTrabalhador(TrabalhadorEntidade trabalhadorEntidade) {
        // Verifica se já existe um trabalhador com o mesmo CPF
        trabalhadorRepository.findByCpf(trabalhadorEntidade.getCpf())
                .ifPresent(usuario -> {
                    throw new TrablhadorExecoes(); // Certifique-se de que TrablhadorExecoes está corretamente definida
                });

        // Garante que o ID seja nulo para novas entidades
        if (trabalhadorEntidade.getId() != null) {
            trabalhadorEntidade.setId(null); // Força a criação de uma nova entidade
        }

        var senhaEncode = passwordEncoder.encode(trabalhadorEntidade.getSenha());
        trabalhadorEntidade.setSenha(senhaEncode);

        trabalhadorRepository.save(trabalhadorEntidade);
    }

    public RespostaLoginDTO logaCanditado(LoginDTO loginDTO){
        var usuario = trabalhadorRepository.findByCpf(loginDTO.getCpf()).orElseThrow(() -> {
            throw new LoginTrabalhadorExepiton("Usuario ou sneha incorretos");
        } );

        var senhaVerdade = passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha());

        if(!senhaVerdade){
            throw new LoginTrabalhadorExepiton("Usuario ou sneha incorretos");
        }
        Algorithm algorithm = Algorithm.HMAC256(cahveSecreta);
        var token = JWT.create().withIssuer("NomeDAempresa").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("trabalhador"))
                .withSubject(usuario.getId().toString()).sign(algorithm);

        return new RespostaLoginDTO(token, usuario.getCpf());

    }

}
