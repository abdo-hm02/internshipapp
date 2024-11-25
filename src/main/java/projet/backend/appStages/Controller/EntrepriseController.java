package projet.backend.appStages.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.Service.EntrepriseService;
import projet.backend.appStages.entity.Entreprise;
import projet.backend.appStages.entity.Professeur;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/ents")
public class EntrepriseController {

    private EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping("/entreprises")
    public String showEntsPage(Model model) {
    	
    	List<Entreprise> ents = entrepriseService.getEntreprises();
        model.addAttribute("entreprises", ents);
        model.addAttribute("entajout", new Entreprise());
        
        return "layouts/entreprises"; // Assuming 'stud' is located in the 'layouts' directory
    }
    
    
    @PostMapping("/ajouterent")
    public String AjouterProf(@ModelAttribute("entajout") Entreprise entreprise, BindingResult result, Model model) throws IdAlreadyExistsException{
    	
    	List<Entreprise> ents = entrepriseService.getEntreprises();
        model.addAttribute("entreprises", ents);
        model.addAttribute("entajout", new Entreprise());
    	entrepriseService.addEntreprise(entreprise);
        
    	return "redirect:/ents/entreprises";
    	
    }
    

    @GetMapping("/confirmdeleteent/{id}")
    public String confirmerdelete(@PathVariable Long id,Model model) throws ElementNotFoundException{
    	
    	
    	model.addAttribute("entId", id);
    	return "layouts/confirmdeleteentreprise";
    }
    
    
    @PostMapping("/confirm-delete-ent")
    public String deleting(@RequestParam Long id, Model model){
    	
    	try {
    		
    		entrepriseService.deleteEntreprise(id);
    		
 
			
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "redirect:/ents/entreprises";
    }
    
    @PostMapping("/confirm-delete-cancel-ent")
    public String canceldeleting(){
    	
    	return "redirect:/ents/entreprises";
    }
    
    
    
    @GetMapping("/showinfoent/{id}")
    public String showstud(@PathVariable Long id, Model model) throws ElementNotFoundException{

    	Entreprise ent = entrepriseService.getEntrepriseById(id);
    	
    	model.addAttribute("ent", ent);
    	
    	return "layouts/entinfo";
    }
    
    
    
    
    
    

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Entreprise> getEntreprises(){
        return entrepriseService.getEntreprises();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Entreprise getEntrepriseById(@PathVariable long id) throws ElementNotFoundException {
        return entrepriseService.getEntrepriseById(id);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Entreprise addEntreprise(@RequestBody @Valid Entreprise entreprise) throws IdAlreadyExistsException {
        return entrepriseService.addEntreprise(entreprise);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEntreprise(@PathVariable long id) throws ElementNotFoundException {
        entrepriseService.deleteEntreprise(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Entreprise updateEntreprise(@PathVariable long id,@RequestBody @Valid Entreprise entreprise) throws ElementNotFoundException {
        return entrepriseService.updateEntreprise(id,entreprise);
    }












}
