package projet.backend.appStages.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.DTO.DateStageDTO;
import projet.backend.appStages.DTO.DateTypeRequestDTO;
import projet.backend.appStages.Service.DateStageService;
import projet.backend.appStages.entity.DateStage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/dates")
public class DateStageController {

    private DateStageService dateStageService;

    public DateStageController(DateStageService dateStageService) {
        this.dateStageService = dateStageService;
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DateStage> getDates(){
        return dateStageService.getDates();
    }


    @GetMapping("/type/{idT}/annee/{annee}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DateStageDTO getDate(@PathVariable int idT,@PathVariable int annee) throws ElementNotFoundException {
        return dateStageService.getDateById(annee, idT);
    }

    @PostMapping("/type/{idT}/annee/{annee}")
    @ResponseStatus(HttpStatus.CREATED)
    public DateStage addDate(@PathVariable int annee, @PathVariable int idT, @RequestBody DateStageDTO dto) throws IdAlreadyExistsException, ElementNotFoundException {
        return dateStageService.addDateStage(annee, idT, dto);
    }

    @PutMapping("/type/{idT}/annee/{annee}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DateStage updateDate(@PathVariable int annee, @PathVariable int idT,@RequestBody DateStageDTO dto) throws ElementNotFoundException {
        return dateStageService.updateDateStage(annee, idT, dto);
    }

    @DeleteMapping("/type/{idT}/annee/{annee}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDate(@PathVariable int annee, @PathVariable int idT) throws ElementNotFoundException {
        dateStageService.deleteDateStage(annee, idT);
    }

    @GetMapping("/type/{idT}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DateTypeRequestDTO> getDatesByType(@PathVariable int idT){
        return dateStageService.getDatesByType(idT);
    }


}
