package com.freela.freelancer.Trabalhadores.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.freela.freelancer.Trabalhadores.DTO.LoginDTO;
import com.freela.freelancer.Trabalhadores.DTO.RespostaLoginDTO;
import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import com.freela.freelancer.Trabalhadores.Repository.TrabalhadorRepository;
import com.freela.freelancer.Trabalhadores.execoes.LoginTrabalhadorExepiton;
import com.freela.freelancer.Trabalhadores.execoes.TrablhadorExecoes;
import com.freela.freelancer.Ultis.RespostaPadrao;
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
    private String cahveSecreta;

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public RespostaPadrao salvaCadastroTrabalhador(TrabalhadorEntidade trabalhadorEntidade) {
        var usaurio = trabalhadorRepository.findByCpf(trabalhadorEntidade.getCpf());
        if (usaurio.isPresent()) {
            return new RespostaPadrao(false, null, "Usuario ja cadastrado");
        }
        if (trabalhadorEntidade.getId() != null) {
            trabalhadorEntidade.setId(null);
        }
        var senhaEncode = passwordEncoder.encode(trabalhadorEntidade.getSenha());
        trabalhadorEntidade.setSenha(senhaEncode);
        trabalhadorRepository.save(trabalhadorEntidade);
        return new RespostaPadrao(true, "", "Cadastro realizado com sucesso");
    }

    public RespostaPadrao logaCanditado(LoginDTO loginDTO) {
        var usuario = trabalhadorRepository.findByCpf(loginDTO.getCpf()).orElse(null);

        if (usuario == null) {
            return new RespostaPadrao(false, null, "Usuario ou senha incorretos");
        }

        var senhaVerdade = passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha());

        if (!senhaVerdade || usuario == null) {
            throw new LoginTrabalhadorExepiton("Usuario ou sneha incorretos");
        }
        Algorithm algorithm = Algorithm.HMAC256(cahveSecreta);
        var token = JWT.create().withIssuer("NomeDAempresa").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("trabalhador"))
                .withSubject(usuario.getId().toString()).sign(algorithm);
        return new RespostaPadrao(true, new RespostaLoginDTO(token, usuario.getCpf()), "");
    }
}