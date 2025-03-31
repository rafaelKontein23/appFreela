package com.freela.freelancer.Trabalhadores.Repository;

import com.freela.freelancer.Trabalhadores.Entity.ProfissaoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfissaoRepository extends JpaRepository<ProfissaoEntidade, UUID> {


    Optional<ProfissaoEntidade> findBynome(String nome);
}
