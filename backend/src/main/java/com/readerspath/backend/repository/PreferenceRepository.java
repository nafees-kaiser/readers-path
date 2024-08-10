package com.readerspath.backend.repository;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Preference findByAppUser(AppUser appUser);
//    AppUserProjection findAppUserByEmail(String email);
//    Preference findByAppUser(AppUser appUserModel);
}
