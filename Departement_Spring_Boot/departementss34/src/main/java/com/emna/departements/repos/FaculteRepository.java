package com.emna.departements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.emna.departements.entities.Faculte;

@RepositoryRestResource(path = "fac")
@CrossOrigin("http://localhost:4200/") //pour autoriser angular

public interface FaculteRepository extends JpaRepository<Faculte, Long> {

}
