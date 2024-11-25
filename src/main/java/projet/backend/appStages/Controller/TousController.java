package projet.backend.appStages.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.DTO.EtudiantRequestDTO;


import projet.backend.appStages.Service.ProfService;
import projet.backend.appStages.Service.EntrepriseService;

import projet.backend.appStages.entity.Entreprise;
import projet.backend.appStages.entity.Professeur;
import projet.backend.appStages.entity.Etudiant;
import projet.backend.appStages.DTO.EtudiantRequestDTO;
import projet.backend.appStages.Service.EtudiantService;
import projet.backend.appStages.entity.Etudiant;


@Controller
@CrossOrigin
public class TousController {
	
	
	private EtudiantService etudiants;
	private ProfService profService;
	private EntrepriseService entrepriseService;
	
	public TousController(EtudiantService etudiants, ProfService profService,EntrepriseService entrepriseService ) {
        this.etudiants = etudiants;
        this.profService = profService;
        this.entrepriseService = entrepriseService;
    }
	
	@GetMapping("/")
    public String home(Model model) {
	   
		List<Etudiant> studentsList = etudiants.getEtudiants();
		List<Professeur> profs = profService.getProfs();
		List<Entreprise> ents = entrepriseService.getEntreprises();
		
		model.addAttribute("entrepriseCount", ents.size());
        model.addAttribute("professorCount", profs.size());
        model.addAttribute("studentCount", studentsList.size());
		
		return "layouts/home";
	}
	
	@GetMapping("/error")
    public String erreur(Model model) {
	   
		
		return "layouts/error";
	}
	
	
	

}
