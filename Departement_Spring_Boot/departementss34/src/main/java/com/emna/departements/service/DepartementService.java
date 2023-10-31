package com.emna.departements.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.emna.departements.entities.Departement;
import com.emna.departements.entities.Faculte;

public interface DepartementService {
	Departement saveDepartement(Departement d, Long id);
	Departement updateDepartement(Departement d);
void deleteDepartement(Departement d);
 void deleteDepartementById(Long id);
 Departement getDepartement(Long id);
List<Departement> getAllDepartements();
List<Faculte> getAllFacultes();
List<Departement> findByNomDepartement(String nom);
List<Departement> findByNomDepartementContains(String nom);
List<Departement> findByNomId (String nom, Long id);
List<Departement> findByFaculte (Faculte faculte);
List<Departement> findByFaculteId(Long id);
List<Departement> findByOrderByNomDepartementAsc();
List<Departement> trierDepartementsNomsId();


Page<Departement> getAllDepartementsParPage(int page, int size);

}

