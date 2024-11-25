package projet.backend.appStages.Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projet.backend.appStages.DTO.EtudiantRequestDTO;
import projet.backend.appStages.Service.EtudiantService;
import projet.backend.appStages.entity.Etudiant;
import projet.backend.appStages.exception.ElementNotFoundException;

import java.util.List;





@CrossOrigin
@Controller
@RequestMapping("/etu")
public class EtudiantController {
	

    private EtudiantService etudiants;
    public EtudiantController(EtudiantService etudiants) {
        this.etudiants = etudiants;
    }
    
    
    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public List<Etudiant> getEtudiants(){
        return etudiants.getEtudiants();
    }
    
    
    
    @GetMapping("/students")
    public String showStudentsPage(Model model) {
    	
        List<Etudiant> studentsList = etudiants.getEtudiants();
        model.addAttribute("students", studentsList);
        model.addAttribute("etuajout",new EtudiantRequestDTO());
        
        return "layouts/stud"; // Assuming 'stud' is located in the 'layouts' directory
    }
    
    
    
    @PostMapping("/ajouteretu")
    public String AjouterEtu(@ModelAttribute("etuajout") EtudiantRequestDTO etudiantDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes){
    	
    	if (result.hasErrors()) {
            return "addEtudiantForm";
        }

        try {
            etudiants.addEtudiant(etudiantDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Étudiant ajouté avec succès");
            List<Etudiant> studentsList = etudiants.getEtudiants();
            model.addAttribute("students", studentsList);
            model.addAttribute("etuajout",new EtudiantRequestDTO());
            
        } catch (ElementNotFoundException ex) {
            // Handle the exception, e.g., add an error message to the model
            model.addAttribute("errorMessage", "Promotion not found");
            return "addEtudiantForm";
        }
    	
    	
    	return "redirect:/etu/students";
    	
    }
    
    
    @GetMapping("/confirmdelete/{id}")
    public String confirmerdelete(@PathVariable Long id, Model model) throws ElementNotFoundException{
    	
    	// CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        // if (csrfToken != null) {
         //   model.addAttribute("_csrf", csrfToken);
        //}
    	Etudiant etud = etudiants.getEtudiantById(id);
    	
    	model.addAttribute("studenttodelete", etud);
    	model.addAttribute("studentId", id);
    	return "layouts/confirmdelete";
    }
    
    @PostMapping("/confirm-delete-student")
    public String deleting(@RequestParam Long id, Model model){
    	
    	try {
    		
    		etudiants.deleteEtudiant(id);
    		
 
			
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "redirect:/etu/students";
    }
    
    @PostMapping("/confirm-delete-student-cancel")
    public String canceldeleting(Model model){
    	
    	return "redirect:/etu/students";
    }
    
    
    @GetMapping("/showinfo/{id}")
    public String showstud(@PathVariable Long id, Model model) throws ElementNotFoundException{

    	Etudiant etud = etudiants.getEtudiantById(id);
    	
    	model.addAttribute("student", etud);
    	
    	return "layouts/studentinfo";
    }
    
    
    
    
    

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Etudiant getEtudiant(@PathVariable long id) throws ElementNotFoundException {
        return etudiants.getEtudiantById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Etudiant addEtudiant(@RequestBody EtudiantRequestDTO etudiant) throws ElementNotFoundException {
        return etudiants.addEtudiant(etudiant);
    }

    @GetMapping("/{nom}/{prenom}")
    public Etudiant getEtudiantByName(@PathVariable String nom, @PathVariable String prenom){
        return etudiants.getEtudiantByName(nom, prenom);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteEtudiant(@PathVariable long id) throws ElementNotFoundException {
        etudiants.deleteEtudiant(id);
    }

    @GetMapping("/promo/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Etudiant> getEtudiantsByPromo(@PathVariable long id){
        return etudiants.getEtudiantsByPromo(id);
    }

    @PutMapping("/{id}/promo/{idP}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void assignEtudiantToPromo(@PathVariable long id, @PathVariable long idP) throws ElementNotFoundException {
        etudiants.assignEtudiantToPromo(id, idP);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Etudiant updateEtudiant(@PathVariable long id, @RequestBody @Valid EtudiantRequestDTO etudiant) throws ElementNotFoundException {
        return etudiants.updateEtudiant(id, etudiant);
    }




}
