package com.freela.freelancer.infrastructure.persistence.repository.feed;

import com.freela.freelancer.infrastructure.persistence.entity.feed.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FeedRepository extends JpaRepository<FeedEntity, UUID> {

    Optional<FeedEntity> findByTrabalhadorEntidadeId(UUID uuid);
}
