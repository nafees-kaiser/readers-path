package com.readerspath.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class BaseEntity <PK extends Serializable> {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private PK id;
}
