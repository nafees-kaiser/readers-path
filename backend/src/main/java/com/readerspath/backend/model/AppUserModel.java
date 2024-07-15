package com.readerspath.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUserModel extends BaseEntity<Long>{
    @Column
    String name;
    String email;
    String password;
    String gender;
    String dob;
    String role;
}