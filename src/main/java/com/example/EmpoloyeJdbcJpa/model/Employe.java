package com.example.EmpoloyeJdbcJpa.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "employe")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Basic(optional = false)
    @Column(name = "nom")
    String nom;

    @Basic
    @Column(name = "prenom")
    String prenom;

    @Basic(optional = false)
    @Column(name = "email")
    String email;
}