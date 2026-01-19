package com.freela.freelancer.application.workers;

import com.freela.freelancer.infrastructure.persistence.entity.workers.ProfissaoEntidade;
import com.freela.freelancer.infrastructure.persistence.repository.workers.ProfissaoRepository;
import com.freela.freelancer.Ultis.RespostaPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfissaoUseCase {
    @Autowired
    private ProfissaoRepository profissaoRepository;

    public RespostaPadrao criarProfissao(String nome) {
         profissaoRepository.findBynome(nome).ifPresent((usuario) -> {
            throw new RuntimeException("Profissão já existe");
        });
         var profissao = new ProfissaoEntidade();
         profissao.setNome(nome);

         profissaoRepository.save(profissao);

        return new RespostaPadrao(true, profissao, "Profissão criada com sucesso");
    }

    public RespostaPadrao buscaProfissoes(){
        var profissoes = profissaoRepository.findAll();
        return new RespostaPadrao(true, profissoes, "");
    }

}
