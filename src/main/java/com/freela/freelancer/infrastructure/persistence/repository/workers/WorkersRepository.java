package com.freela.freelancer.infrastructure.persistence.repository.workers;

import com.freela.freelancer.infrastructure.persistence.entity.workers.WorkersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkersRepository extends JpaRepository<WorkersEntity, UUID> {

    Optional<WorkersEntity>  findByCpf(String cpf);

}
