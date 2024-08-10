package com.readerspath.backend.repository;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    Optional<Reward> findByAppUser(AppUser appUser);
}
