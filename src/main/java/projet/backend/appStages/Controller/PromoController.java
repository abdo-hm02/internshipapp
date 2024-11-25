package projet.backend.appStages.Controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projet.backend.appStages.DTO.PromoRequestDTO;
import projet.backend.appStages.Service.PromoService;
import projet.backend.appStages.entity.Promotion;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/promos")
public class PromoController {


    private PromoService promoService;

    public PromoController(PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Promotion> getPromos(){
        return promoService.getPromos();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Promotion getPromoById(@PathVariable long id) throws ElementNotFoundException {
        return promoService.getPromoById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Promotion addPromo(@RequestBody @Valid PromoRequestDTO promotion) throws IdAlreadyExistsException, ElementNotFoundException {
        return promoService.addPromo(promotion);
    }

    @GetMapping("/etudiant/{nom}/{prenom}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Promotion getPromoByEtudiant(@PathVariable String nom, @PathVariable String prenom){
        return promoService.getPromoByEtudiant(nom, prenom);
    }

    @GetMapping("/prof/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Promotion> getPromosByProf(@PathVariable long id){
        return promoService.getPromosByProf(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePromo(@PathVariable long id) throws ElementNotFoundException {
        promoService.deletePromo(id);
    }

    @PutMapping("/{id}/prof/{idP}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Promotion assignProfToPromo(@PathVariable long id, @PathVariable long idP) throws ElementNotFoundException {
        return promoService.assignProfToPromo(id, idP);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Promotion updatePromo(@PathVariable long id, @RequestBody @Valid PromoRequestDTO promotion) throws ElementNotFoundException {
        return promoService.updatePromo(id, promotion);
    }





}
