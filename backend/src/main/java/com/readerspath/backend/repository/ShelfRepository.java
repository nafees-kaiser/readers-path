package com.readerspath.backend.repository;

import com.readerspath.backend.model.AppUser;
import com.readerspath.backend.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    List<Shelf> findAllByAppUser(AppUser appUser);
}
