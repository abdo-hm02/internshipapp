package projet.backend.appStages.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import projet.backend.appStages.DTO.PromoRequestDTO;
import projet.backend.appStages.entity.Promotion;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.PromoRepository;

import java.util.List;


@Transactional
@Service
public class PromoService {
    private PromoRepository promoRepository;
    private ProfService profService;

    public PromoService(PromoRepository promoRepository, ProfService profService) {
        this.promoRepository = promoRepository;
        this.profService = profService;
    }

    public Promotion convertDTOToPromo(PromoRequestDTO dto) throws ElementNotFoundException {
        Promotion promotion = new Promotion();
        promotion.setId(dto.getId());
        promotion.setNbreInscrits(dto.getNbreInscrits());
        promotion.setNbreRecus(dto.getNbreRecus());
        if(dto.getIdProf()!=0) promotion.setProfesseur(profService.getProfById(dto.getIdProf()));
        return promotion;
    }

    public Promotion getPromoById(long id) throws ElementNotFoundException {
        if (promoRepository.existsById(id)){
            return promoRepository.findById(id).get();
        }else{
            throw new ElementNotFoundException("promo pas trouvée!");
        }
    }

    public List<Promotion> getPromos(){
        return promoRepository.findAll();
    }

    public Promotion addPromo(PromoRequestDTO promotion) throws IdAlreadyExistsException, ElementNotFoundException {
        if (promoRepository.existsById(promotion.getId())) throw new IdAlreadyExistsException("La promo existe déjà!");
        else {
            Promotion promotion1 = convertDTOToPromo(promotion);
            return promoRepository.save(promotion1);
        }
    }
    public Promotion getPromoByEtudiant(String nom, String prenom){
        return promoRepository.findByEtudiantsNomAndEtudiantsPrenom(nom, prenom);
    }

    public Promotion assignProfToPromo(long idPromo, long idProf) throws ElementNotFoundException {
        Promotion promotion = getPromoById(idPromo);
        promotion.setProfesseur(profService.getProfById(idProf));
        return promoRepository.save(promotion);
    }


    public void deletePromo(long id) throws ElementNotFoundException {
        getPromoById(id).getEtudiants().forEach(etudiant -> {
            etudiant.setPromo(null);
        });
        promoRepository.deleteById(id);
    }


    public List<Promotion> getPromosByProf(long id){
        return promoRepository.findByProfesseurId(id);
    }

    public Promotion updatePromo(long id, PromoRequestDTO promotion) throws ElementNotFoundException {
        Promotion p = getPromoById(id);
        p.setNbreRecus(promotion.getNbreRecus());
        p.setNbreInscrits(promotion.getNbreInscrits());
        if(promotion.getIdProf()!=0) p.setProfesseur(profService.getProfById(promotion.getIdProf()));
        return promoRepository.save(p);
    }
    
    public int getNombreEtudiants(long id) throws ElementNotFoundException {
        return getPromoById(id).getEtudiants().toArray().length;
    }

}

