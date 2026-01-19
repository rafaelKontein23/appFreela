package com.freela.freelancer.workers.Repository;

import com.freela.freelancer.workers.Entity.WorkersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkersRepository extends JpaRepository<WorkersEntity, UUID> {

    Optional<WorkersEntity>  findByCpf(String cpf);

}
