package com.example.EmpoloyeJdbcJpa.repository;

import com.example.EmpoloyeJdbcJpa.model.Employe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeRepository extends CrudRepository<Employe, Integer> { }