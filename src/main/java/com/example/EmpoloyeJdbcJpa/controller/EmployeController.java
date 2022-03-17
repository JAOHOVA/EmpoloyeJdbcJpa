package com.example.EmpoloyeJdbcJpa.controller;

import com.example.EmpoloyeJdbcJpa.model.Employe;
import com.example.EmpoloyeJdbcJpa.repository.EmployeRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/employes")
public class EmployeController {

    EmployeRepository employeRepository;

    @SneakyThrows
    @PostMapping(value = "/post")
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) {
        try {
            employeRepository.save ( employe );
            log.info ( "L'employé a bel et bien été rajouté" );
            return new ResponseEntity<> ( employe, HttpStatus.CREATED );
        } catch (Exception e) {
            log.warning ( "L'employé n'a pas pu être rajouté" );
            return new ResponseEntity<> ( null, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @SneakyThrows
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable(value = "id") int employeId) {
        log.info ( " Méthode de gestion de requête HTTP GET pour trouver un employé par son id" );
        Employe employe = employeRepository.findById ( employeId ).orElse ( null );
        return new ResponseEntity<> ( employe, HttpStatus.OK );
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = new ArrayList<> ( );
        employeRepository.findAll ( ).forEach ( employes::add );
        log.info ( "La liste des produit est bel et bien renvoyée!" );
        return new ResponseEntity<> ( employes, HttpStatus.OK );
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Employe> updateEmploye(
            @PathVariable(value = "id") int employeId,
            @RequestBody Employe employe) {
        Optional<Employe> employeData = employeRepository.findById ( employeId );
        if (employeData.isPresent ( )) {
            Employe employeUpdate = employeData.get ( );
            employeUpdate.setNom ( employe.getNom ( ) );
            employeUpdate.setPrenom ( employe.getPrenom ( ) );
            employeUpdate.setEmail ( employe.getEmail ( ) );
            log.info ( "L'employé a bel et bien été renouvelé" );
            employeRepository.save ( employeUpdate );
            return new ResponseEntity<Employe> ( employeUpdate, HttpStatus.OK );
        }
        log.warning ( "L'employé n'a pas pu être renouvelé" );
        return new ResponseEntity<> ( employe, HttpStatus.OK );
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeById(@PathVariable(value = "id") int employeId) {
        log.info ( "L'employé a été retirés de la liste avec succes!" );
        employeRepository.deleteById ( employeId );
        return new ResponseEntity<> ( HttpStatus.NO_CONTENT );
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/")
    public ResponseEntity<HttpStatus> deleteAllEmployes() {
        log.info ( "Tous les employés ont été retirés de la liste avec succes!" );
        employeRepository.deleteAll ( );
        return new ResponseEntity<> ( HttpStatus.NO_CONTENT );
    }
}