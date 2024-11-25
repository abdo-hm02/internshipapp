package projet.backend.appStages.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.DTO.TuteurRequestDTO;
import projet.backend.appStages.Service.TuteurService;
import projet.backend.appStages.entity.Tuteur;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.repository.TuteurRepository;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tuteurs")
public class TuteurController {

    private TuteurService tuteurService;

    public TuteurController(TuteurService tuteurService) {
        this.tuteurService = tuteurService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Tuteur> getTuteurs(){
        return tuteurService.getTuteurs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Tuteur getTuteurById(@PathVariable long id) throws ElementNotFoundException {
        return tuteurService.getTuteurById(id);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Tuteur addTuteur(@RequestBody TuteurRequestDTO tuteur) throws ElementNotFoundException {
        return tuteurService.addTuteur(tuteur);
    }

    @GetMapping("/{nom}/{prenom}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Tuteur getTuteurByNomAndPrenom(@PathVariable String nom, @PathVariable String prenom) throws ElementNotFoundException {
        return tuteurService.getTuteurByNomAndPrenom(nom, prenom);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTuteur(@PathVariable long id) throws ElementNotFoundException {
        tuteurService.deleteTuteur(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Tuteur updateTuteur(@PathVariable long id, @RequestBody TuteurRequestDTO tuteurRequestDTO) throws ElementNotFoundException {
        return tuteurService.updateTuteur(id,tuteurRequestDTO);
    }


    @GetMapping("/entreprise/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Tuteur> getTuteursByEntrepriseId(@PathVariable long id){
        return tuteurService.getTuteursByEntrepriseId(id);
    }
    





}
