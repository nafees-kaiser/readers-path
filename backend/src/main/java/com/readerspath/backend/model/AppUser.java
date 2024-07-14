package com.readerspath.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser extends BaseEntity<Long>{
    @Column
    private String name;
    private String email;
    private String password;
    private String gender;
    private String dob;
    private String role;
}
