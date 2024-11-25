package projet.backend.appStages.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projet.backend.appStages.DTO.EtudiantRequestDTO;
import projet.backend.appStages.Service.ProfService;
import projet.backend.appStages.entity.Etudiant;
import projet.backend.appStages.entity.Professeur;
import projet.backend.appStages.exception.ElementNotFoundException;

import java.util.List;


@Controller
@CrossOrigin
@RequestMapping("/profs")
public class ProfController {

    private ProfService profService;

    public ProfController(ProfService profService) {
        this.profService = profService;
    }
    
    @GetMapping("/professeurs")
    public String showProfsPage(Model model) {
    	
        List<Professeur> profs = profService.getProfs();
        model.addAttribute("professors", profs);
        model.addAttribute("profajout", new Professeur());
        
        return "layouts/proffs"; // Assuming 'stud' is located in the 'layouts' directory
    }
    
    
    
    
    @PostMapping("/ajouterprof")
    public String AjouterProf(@ModelAttribute("profajout") Professeur professeur, BindingResult result, Model model){
    	
    	
        profService.addProf(professeur);
        
        List<Professeur> profs = profService.getProfs();
        model.addAttribute("professors", profs);
        model.addAttribute("profajout",new Professeur());
    	return "redirect:/profs/professeurs";
    	
    }
    
    
    
    @GetMapping("/confirmdeleteprof/{id}")
    public String confirmerdelete(@PathVariable Long id, Model model) throws ElementNotFoundException{
    	
    	Professeur prof = profService.getProfById(id);
    	
    	model.addAttribute("proftodelete", prof);
    	model.addAttribute("profId", id);
    	return "layouts/confirmdeleteprof";
    }
    
    
    @PostMapping("/confirm-delete-prof")
    public String deleting(@RequestParam Long id, Model model){
    	
    	try {
    		
    		profService.deleteProf(id);
    		
 
			
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "redirect:/profs/professeurs";
    }
    
    @PostMapping("/confirm-delete-cancel-prof")
    public String canceldeleting(){
    	
    	return "redirect:/profs/professeurs";
    }
   
    
    @GetMapping("showinfoprof/{id}")
    public String showstud(@PathVariable Long id, Model model) throws ElementNotFoundException{

    	Professeur prof = profService.getProfById(id);
    	
    	model.addAttribute("prof", prof);
    	
    	return "layouts/profinfo";
    }
    
    

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Professeur> getProfs(){
        return profService.getProfs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Professeur getProfById(@PathVariable long id) throws ElementNotFoundException {
        return profService.getProfById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Professeur addProf(@RequestBody @Valid Professeur professeur){
        return profService.addProf(professeur);
    }

    @GetMapping("/{nom}/{prenom}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Professeur getProfByNomAndPrenom(@PathVariable String nom,@PathVariable String prenom) throws ElementNotFoundException {
        return profService.getProfByNomAndPrenom(nom, prenom);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProf(@PathVariable long id) throws ElementNotFoundException {
        profService.deleteProf(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Professeur updateProf(@PathVariable long id, @RequestBody @Valid Professeur professeur) throws ElementNotFoundException {
        return profService.updateProf(id, professeur);
    }






}
