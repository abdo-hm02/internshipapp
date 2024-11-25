package projet.backend.appStages.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.Service.TypeStageService;
import projet.backend.appStages.entity.TypeStage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.TypeStageRepository;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/types")
public class TypeStageController {

    private TypeStageService typeStageService;

    public TypeStageController(TypeStageService typeStageService) {
        this.typeStageService = typeStageService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TypeStage> getTypes(){
        return typeStageService.getTypes();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TypeStage getTypeById(@PathVariable int id) throws ElementNotFoundException {
        return typeStageService.getTypeById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TypeStage addType(@RequestBody TypeStage typeStage) throws IdAlreadyExistsException {
        return typeStageService.addType(typeStage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteType(@PathVariable int id) throws ElementNotFoundException {
        typeStageService.deleteType(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TypeStage updateType(@PathVariable int id, @RequestBody TypeStage typeStage) throws ElementNotFoundException {
        return typeStageService.updateType(id, typeStage);
    }



}
