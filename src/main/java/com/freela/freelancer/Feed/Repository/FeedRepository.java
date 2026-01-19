package com.freela.freelancer.Feed.Repository;

import com.freela.freelancer.Feed.Entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FeedRepository extends JpaRepository<FeedEntity, UUID> {

    Optional<FeedEntity> findByTrabalhadorEntidadeId(UUID uuid);
}
