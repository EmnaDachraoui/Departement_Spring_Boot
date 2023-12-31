package com.emna.departements.restcontrollers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emna.departements.entities.Departement;
import com.emna.departements.service.DepartementService;
@RestController
@RequestMapping("/api")
@CrossOrigin
public class DepartementRESTController {
@Autowired
DepartementService departementService;
@RequestMapping(method = RequestMethod.GET)
public List<Departement> getAllDepartements() {
return departementService.getAllDepartements();
}

@RequestMapping(value="/{id}",method = RequestMethod.GET)
public Departement getDepartementById(@PathVariable("id") Long id) {
return departementService.getDepartement(id);
 }

@RequestMapping(method = RequestMethod.POST)
public Departement createDepartement(@RequestBody Departement departement) {
return departementService.saveDepartement(departement, Long.parseLong("1".toString()));
}

@RequestMapping(method = RequestMethod.PUT)
public Departement updateDepartement(@RequestBody Departement departement) {
return departementService.updateDepartement(departement);
}


@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
public void deleteDepartement(@PathVariable("id") Long id)
{
departementService.deleteDepartementById(id);
}


@RequestMapping(value="/depsfac/{id}",method = RequestMethod.GET)
public List<Departement> getDepartementsByFacId(@PathVariable("id") Long id) {
return departementService.findByFaculteId(id);
}

@RequestMapping(value="/depsByName/{nom}",method = RequestMethod.GET)
public List<Departement> findByNomDepartementContains(@PathVariable("nom") String nom) {
return departementService.findByNomDepartementContains(nom);
}

}

