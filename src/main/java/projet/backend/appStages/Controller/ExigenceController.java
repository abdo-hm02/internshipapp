package projet.backend.appStages.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.DTO.TypeExigenceRequestDTO;
import projet.backend.appStages.Service.TypeExigeCompetenceService;
import projet.backend.appStages.entity.TypeExigeCompetence;
import projet.backend.appStages.exception.ElementNotFoundException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/exigences")
public class ExigenceController {

    private TypeExigeCompetenceService typeExigeCompetenceService;

    public ExigenceController(TypeExigeCompetenceService typeExigeCompetenceService) {
        this.typeExigeCompetenceService = typeExigeCompetenceService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TypeExigeCompetence> getExigences(){
        return typeExigeCompetenceService.getExigences();
    }

    @GetMapping("/type/{idT}/competence/{idC}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Integer> getExigenceById(@PathVariable int idT, @PathVariable int idC) throws ElementNotFoundException {
        return typeExigeCompetenceService.getExigenceById(idT, idC);
    }

    @PostMapping("/type/{idT}/competence/{idC}")
    @ResponseStatus(HttpStatus.CREATED)
    public TypeExigeCompetence addCompetenceToType(@PathVariable int idT, @PathVariable int idC,@RequestBody int niv) throws ElementNotFoundException {
        return typeExigeCompetenceService.addCompetenceToType(idT, idC, niv);
    }


    @DeleteMapping("/type/{idT}/competence/{idC}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteExigence(@PathVariable int idT, @PathVariable int idC) throws ElementNotFoundException {
        typeExigeCompetenceService.deleteExigence(idT, idC);
    }

    @GetMapping("/type/{id}")
    @ResponseStatus
    public List<TypeExigenceRequestDTO> getCompetencesByType(@PathVariable int id){
        return typeExigeCompetenceService.getCompetencesByType(id);
    }

    @PutMapping("/type/{idT}/competence/{idC}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TypeExigeCompetence updateExigence(@PathVariable int idT,@PathVariable int idC, @RequestBody int niv) throws ElementNotFoundException {
        return typeExigeCompetenceService.updateExigence(idT, idC, niv);
    }



}
