package com.readerspath.backend.repository;

import com.readerspath.backend.model.AppUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserModel, Long> {
    AppUserModel findByEmail(String email);
}
