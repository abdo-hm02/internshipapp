package projet.backend.appStages.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.entity.Professeur;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.repository.ProfRepository;
import projet.backend.appStages.repository.PromoRepository;
import projet.backend.appStages.repository.StageRepository;

import java.util.List;
@Transactional
@Service
public class ProfService {
    private ProfRepository profRepository;
    private PromoRepository promoRepository;
    private StageRepository stageRepository;

    public ProfService(ProfRepository profRepository, PromoRepository promoRepository, StageRepository stageRepository) {
        this.profRepository = profRepository;
        this.promoRepository = promoRepository;
        this.stageRepository = stageRepository;
    }

    public Professeur getProfById(long id) throws ElementNotFoundException {
        if (profRepository.existsById(id)){
            return profRepository.findById(id).get();
        }else{
            throw new ElementNotFoundException("Le professeur n'existe pas!!");
        }
    }

    public List<Professeur> getProfs(){
        return profRepository.findAll();
    }

    public Professeur addProf(Professeur prof){
        return profRepository.save(prof);
    }

    public Professeur getProfByNomAndPrenom(String nom, String prenom) throws ElementNotFoundException {
            if (profRepository.existsByNomAndPrenom(nom, prenom)) return profRepository.findByNomAndPrenom(nom,prenom);
            else throw new ElementNotFoundException("Le professeur n'existe pas!");
    }

    public void deleteProf(long id) throws ElementNotFoundException {
        if (profRepository.existsById(id)) {
            /*getProfById(id).getPromotionSet().forEach(promotion -> {
                promotion.setProfesseur(null);
                promoRepository.save(promotion);
            });*/
        	
        	promoRepository.deleteByProfesseurId(id);
            getProfById(id).getStages().forEach(stage -> {
                stage.setProfesseur(null);
                stageRepository.save(stage);
            });
            profRepository.deleteById(id);

        } else {
                throw new ElementNotFoundException("Le prof n'existe pas!");
        }
    }

    public Professeur updateProf(long id, Professeur professeur) throws ElementNotFoundException {
        if (profRepository.existsById(id)){
            Professeur p = getProfById(id);
            p.setAdresse(professeur.getAdresse());
            p.setNom(professeur.getNom());
            p.setPrenom(professeur.getPrenom());
            p.setTelDomicile(professeur.getTelDomicile());
            p.setTelEcole(professeur.getTelEcole());
            p.setVille(professeur.getVille());
            p.setCodePostal(professeur.getCodePostal());
            p.setQualite(professeur.getQualite());
            return profRepository.save(p);
        }else throw new ElementNotFoundException("Le prof n'existe pas!");
    }


}
