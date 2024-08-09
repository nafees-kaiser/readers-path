package com.readerspath.backend.repository;

import com.readerspath.backend.model.Questionaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionariesRepository extends JpaRepository<Questionaries, Long> {
}
