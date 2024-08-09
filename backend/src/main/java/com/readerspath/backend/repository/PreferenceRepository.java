package com.readerspath.backend.repository;

import com.readerspath.backend.model.Preference;
//import com.readerspath.backend.projection.AppUserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Long> {
//    AppUserProjection findAppUserByEmail(String email);
//    Preference findByAppUser(AppUser appUserModel);
}
