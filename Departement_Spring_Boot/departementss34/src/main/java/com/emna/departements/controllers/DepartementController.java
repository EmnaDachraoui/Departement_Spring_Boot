package com.emna.departements.controllers;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emna.departements.entities.Departement;
import com.emna.departements.entities.Faculte;
import com.emna.departements.service.DepartementService;
@Controller
public class DepartementController {
@Autowired
DepartementService departementService;

@RequestMapping("/showCreate")
public String showCreate(ModelMap modelMap)
{
List<Faculte> facs = departementService.getAllFacultes();
Departement dep = new Departement();
Faculte fac = new Faculte();
fac = facs.get(0); // prendre la première faculte de la liste
dep.setFaculte(fac); //affecter une faculte par défaut au dep pour éviter le pb avec une faculte nulle
modelMap.addAttribute("departement",dep);
modelMap.addAttribute("mode", "new");
modelMap.addAttribute("facultes", facs);
modelMap.addAttribute("page",0);
return "formDepartement";
}

@RequestMapping("/saveDepartement")
public String saveDepartement(@Valid Departement departement,
BindingResult bindingResult,
@ModelAttribute("page") int pageFromPrevious,
@ModelAttribute ("genre") String fc,
@RequestParam (name="size", defaultValue = "3") int size,
RedirectAttributes redirectAttributes)
{
int page;
if (bindingResult.hasErrors()) return "formDepartement";
if (departement.getIdDepartement()==null) //adding
    page = departementService.getAllDepartements().size()/size; // calculer le nbr de pages
else //updating
    page = pageFromPrevious;
departementService.saveDepartement(departement, Long.parseLong(fc.toString()));
redirectAttributes.addAttribute("page", page);
return "redirect:/ListeDepartements";
}


@RequestMapping("/ListeDepartements")
public String listeDepartements(ModelMap modelMap,
              @RequestParam (name="page",defaultValue = "0") int page,
              @RequestParam (name="size", defaultValue = "3") int size)
{
	Page<Departement> deps = departementService.getAllDepartementsParPage(page, size);
	modelMap.addAttribute("departements", deps);
	 modelMap.addAttribute("pages", new int[deps.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	return "listeDepartements";
}


@RequestMapping("/supprimerDepartement")
public String supprimerDepartement(@RequestParam("id") Long id,
 ModelMap modelMap, 
 @RequestParam (name="page",defaultValue = "0") int page,
 @RequestParam (name="size", defaultValue = "3") int size)
{ 
	departementService.deleteDepartementById(id);
	Page<Departement> deps = departementService.getAllDepartementsParPage(page, 
			size);
			modelMap.addAttribute("departements", deps);
			modelMap.addAttribute("pages", new int[deps.getTotalPages()]);
			modelMap.addAttribute("currentPage", page);
			modelMap.addAttribute("size", size);
			return "listeDepartements";
}
	

@RequestMapping("/modifierDepartement")
public String editerProduit(@RequestParam("id") Long id,
			@RequestParam("page") int page,
			ModelMap modelMap)
{
	Departement d= departementService.getDepartement(id);
	List<Faculte> facs = departementService.getAllFacultes();
modelMap.addAttribute("departement", d);
modelMap.addAttribute("mode", "edit");
modelMap.addAttribute("facultes", facs);
modelMap.addAttribute("page",page);
return "formDepartement";
}

@RequestMapping("/updateDepartement")
public String updateDepartement(@ModelAttribute("departement") Departement departement,
ModelMap modelMap) throws ParseException
{
	departementService.updateDepartement(departement);
 List<Departement> deps = departementService.getAllDepartements();
 modelMap.addAttribute("departements", deps);
return "listeDepartements";
}
}

