package projet.backend.appStages.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import projet.backend.appStages.DTO.EtudiantRequestDTO;
import projet.backend.appStages.DTO.StageRequestDTO;
import projet.backend.appStages.Service.StageService;
import projet.backend.appStages.entity.Entreprise;
import projet.backend.appStages.entity.Etudiant;
import projet.backend.appStages.entity.Stage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.StageRepository;

import java.util.List;

@Controller
@RequestMapping("/sts")
@CrossOrigin
public class StageController {
	
	private StageRepository stageRepository;
    private StageService stageService;
    public StageController(StageService stageService, StageRepository stageRepository) {
        this.stageService = stageService;
        this.stageRepository = stageRepository;
    }
    
    
    
    @GetMapping("/stages")
    public String showStagessPage(Model model) {
    	
    	List<Stage> stages = stageService.getInternships();
        model.addAttribute("stages", stages);
        model.addAttribute("stageajout",new Stage());
        
        return "layouts/stages"; // Assuming 'stud' is located in the 'layouts' directory
    }
    
    @PostMapping("/ajouterstage")
    public String AjouterProf(@ModelAttribute("stageajout") Stage stage, BindingResult result, Model model) throws IdAlreadyExistsException, ElementNotFoundException{
    	
    	
        
    	StageRequestDTO dto = stageService.convertToDTO(stage);
    	
    	stageService.addStage(dto);
        
    	return "redirect:/sts/stages";
    	
    }
    
    @GetMapping("/confirmdeletestage/{id}")
    public String confirmerdelete(@PathVariable Long id,Model model) throws ElementNotFoundException{
    	
    	
    	model.addAttribute("stageId", id);
    	return "layouts/confirmdeletestage";
    }
    
    
    @PostMapping("/confirm-delete-stage")
    public String deleting(@RequestParam Long id, Model model){
    	
    		stageRepository.deleteById(id);
    	
    	return "redirect:/sts/stages";
    }
    
    @PostMapping("/confirm-delete-cancel-stage")
    public String canceldeleting(){
    	
    	return "redirect:/sts/stages";
    }
    
    @GetMapping("/showinfostage/{id}")
    public String showstud(@PathVariable Long id, Model model) throws ElementNotFoundException{

    	Stage stage = stageRepository.findById(id).get();
    	
    	model.addAttribute("stage", stage);
    	
    	return "layouts/stageinfo";
    }
    
    
    
    
    
    
    
    
    
    

    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Stage> getStages(){
        return stageService.getInternships();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StageRequestDTO getStageById(@PathVariable long id) throws ElementNotFoundException {
        return stageService.getStageById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public StageRequestDTO addStage(@RequestBody StageRequestDTO dto) throws ElementNotFoundException {
        return stageService.addStage(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StageRequestDTO updateStage(@PathVariable long id, @RequestBody StageRequestDTO dto) throws ElementNotFoundException {
        return stageService.updateStage(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStage(@PathVariable long id) throws ElementNotFoundException {
        stageService.deleteStage(id);
    }

    @GetMapping("/etudiant/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StageRequestDTO> getStageByEtudiant(@PathVariable long id){
        return stageService.getStagesByEtudiant(id);
    }

    @GetMapping("/entreprise/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StageRequestDTO> getStagesByEntreprise(@PathVariable long id){
        return stageService.getStagesByEntreprise(id);
    }

    @GetMapping("/tuteur/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StageRequestDTO> getStagesByTuteur(@PathVariable long id){
        return stageService.getStagesByTuteur(id);
    }

    @GetMapping("/prof/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StageRequestDTO> getStagesByProf(@PathVariable long id){
        return stageService.getStagesByProf(id);
    }

    @GetMapping("/type/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StageRequestDTO> getStagesByType(@PathVariable int id){
        return stageService.getStagesByType(id);
    }

    @GetMapping("/annee/{annee}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StageRequestDTO> getStagesByAnnee(@PathVariable int annee){
        return stageService.getStagesByAnnee(annee);
    }

    @GetMapping("/etudiant/{idE}/type/{idT}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StageRequestDTO> getStagesByEtudiantAndType(@PathVariable long idE, @PathVariable int idT){
        return stageService.getStagesByEtudiantAndType(idE, idT);
    }













}
