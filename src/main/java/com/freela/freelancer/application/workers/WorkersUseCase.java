package com.freela.freelancer.application.workers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.freela.freelancer.presentation.workers.dto.LoginDTO;
import com.freela.freelancer.presentation.workers.dto.ResponseLoginDTO;
import com.freela.freelancer.infrastructure.persistence.entity.workers.WorkersEntity;
import com.freela.freelancer.infrastructure.persistence.repository.workers.WorkersRepository;
import com.freela.freelancer.infrastructure.persistence.repository.workers.exceptions.LoginWorkersExceptions;
import com.freela.freelancer.Ultis.RespostaPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class WorkersUseCase {
    @Value("${security.token.secret.trabalhador}")
    private String cahveSecreta;

    @Autowired
    private WorkersRepository trabalhadorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public RespostaPadrao salvaCadastroTrabalhador(WorkersEntity trabalhadorEntidade) {
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
            throw new LoginWorkersExceptions("Usuario ou sneha incorretos");
        }
        Algorithm algorithm = Algorithm.HMAC256(cahveSecreta);
        var token = JWT.create().withIssuer("NomeDAempresa").withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("trabalhador"))
                .withSubject(usuario.getId().toString()).sign(algorithm);
        return new RespostaPadrao(true, new ResponseLoginDTO(token, usuario.getCpf()), "");
    }
}