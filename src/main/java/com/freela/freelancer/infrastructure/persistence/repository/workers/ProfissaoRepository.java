package com.freela.freelancer.infrastructure.persistence.repository.workers;

import com.freela.freelancer.infrastructure.persistence.entity.workers.ProfissaoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfissaoRepository extends JpaRepository<ProfissaoEntidade, UUID> {


    Optional<ProfissaoEntidade> findBynome(String nome);
}
