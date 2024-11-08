package org.baas.baascore.repository;

import org.baas.baascore.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe,Long> {
    Optional<Subscribe> findByAccessKey(String accessKey);
}
