package projet.backend.appStages.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.DTO.TuteurRequestDTO;
import projet.backend.appStages.entity.Tuteur;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.repository.StageRepository;
import projet.backend.appStages.repository.TuteurRepository;

import java.util.List;
@Transactional
@Service
public class TuteurService {

    private TuteurRepository tuteurRepository;
    private EntrepriseService entrepriseService;
    private StageRepository stageRepository;

    public TuteurService(TuteurRepository tuteurRepository, EntrepriseService entrepriseService, StageRepository stageRepository) {
        this.tuteurRepository = tuteurRepository;
        this.entrepriseService = entrepriseService;
        this.stageRepository = stageRepository;
    }

    public Tuteur convertDTOToTuteur(TuteurRequestDTO tuteurRequestDTO) throws ElementNotFoundException {
        Tuteur tuteur = new Tuteur();
        tuteur.setNom(tuteurRequestDTO.getNom());
        tuteur.setPrenom(tuteurRequestDTO.getPrenom());
        tuteur.setTel(tuteurRequestDTO.getTel());
        tuteur.setQualite(tuteurRequestDTO.getQualite());
        if (tuteurRequestDTO.getIdEntreprise()!=0) tuteur.setEntreprise(entrepriseService.getEntrepriseById(tuteurRequestDTO.getIdEntreprise()));
        return tuteur;
    }

    public List<Tuteur> getTuteurs(){
        return tuteurRepository.findAll();
    }

    public Tuteur getTuteurById(long id) throws ElementNotFoundException {
        if (tuteurRepository.existsById(id)) return tuteurRepository.findById(id).get();
        else throw new ElementNotFoundException("Le tuteur n'existe pas!");
    }

    public Tuteur addTuteur(TuteurRequestDTO tuteurRequestDTO) throws ElementNotFoundException {
        Tuteur tuteur = convertDTOToTuteur(tuteurRequestDTO);
        return tuteurRepository.save(tuteur);
    }

    public void deleteTuteur(long id) throws ElementNotFoundException {
        if (tuteurRepository.existsById(id)) {
            getTuteurById(id).getStages().forEach(stage -> {
                stage.setTuteur(null);
                stageRepository.save(stage);
            });
            tuteurRepository.deleteById(id);
        }else throw new ElementNotFoundException("Le tuteur n'existe pas!");
    }

    public Tuteur getTuteurByNomAndPrenom(String nom, String prenom) throws ElementNotFoundException {
        if (tuteurRepository.existsByNomAndPrenom(nom, prenom))
            return tuteurRepository.findByNomAndPrenom(nom, prenom);
        else throw new ElementNotFoundException("Le tuteur n'existe pas!");
    }

    public Tuteur updateTuteur(long id,TuteurRequestDTO tuteurRequestDTO) throws ElementNotFoundException {
        Tuteur tuteur = getTuteurById(id);
        tuteur.setNom(tuteurRequestDTO.getNom());
        tuteur.setPrenom(tuteurRequestDTO.getPrenom());
        tuteur.setQualite(tuteurRequestDTO.getQualite());
        tuteur.setTel(tuteurRequestDTO.getTel());
        if(tuteurRequestDTO.getIdEntreprise()!=0) tuteur.setEntreprise(entrepriseService.getEntrepriseById(tuteurRequestDTO.getIdEntreprise()));
        return tuteurRepository.save(tuteur);
    }


    public List<Tuteur> getTuteursByEntrepriseId(long id){
        return tuteurRepository.findByEntrepriseId(id);
    }



















}
