package projet.backend.appStages.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.Service.AnneeService;
import projet.backend.appStages.entity.Annee;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/annees")
public class AnneeController {

    private AnneeService anneeService;

    public AnneeController(AnneeService anneeService) {
        this.anneeService = anneeService;
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Annee> getAnnees(){
        return anneeService.getAnnees();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Annee getAnnee(@PathVariable int id) throws ElementNotFoundException {
        return anneeService.getAnnee(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Annee addAnnee(@PathVariable int id) throws IdAlreadyExistsException {
        return anneeService.addAnnee(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAnnee(@PathVariable int id) throws ElementNotFoundException {
        anneeService.deleteAnnee(id);
    }



}
