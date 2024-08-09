package com.readerspath.backend.repository;

import com.readerspath.backend.model.LinksToBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksToBuyRepository extends JpaRepository<LinksToBuy,Long> {
}
