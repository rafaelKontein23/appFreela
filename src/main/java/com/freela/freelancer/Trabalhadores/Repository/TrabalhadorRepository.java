package com.freela.freelancer.Trabalhadores.Repository;

import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TrabalhadorRepository extends JpaRepository<TrabalhadorEntidade, UUID> {

    Optional<TrabalhadorEntidade>  findByCpf(String cpf);

}
