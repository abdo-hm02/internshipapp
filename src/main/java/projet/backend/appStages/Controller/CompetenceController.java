package projet.backend.appStages.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.Service.CompetenceService;
import projet.backend.appStages.entity.Competence;
import projet.backend.appStages.exception.ElementNotFoundException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/competences")
public class CompetenceController {
    private CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Competence> getCompetences(){
        return competenceService.getCompetences();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Competence getCompetenceById(@PathVariable int id) throws ElementNotFoundException {
        return competenceService.getCompetenceById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Competence addCompetence(@RequestBody @Valid Competence competence){
        return competenceService.addCompetence(competence);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCompetence(@PathVariable int id) throws ElementNotFoundException {
        competenceService.deleteCompetence(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Competence updateCompetence(@PathVariable int id, @Valid @RequestBody Competence competence) throws ElementNotFoundException {
        return competenceService.updateCompetence(id, competence);
    }







}
