package projet.backend.appStages.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.entity.Entreprise;
import projet.backend.appStages.entity.Tuteur;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.EntrepriseRepository;
import projet.backend.appStages.repository.StageRepository;
import projet.backend.appStages.repository.TuteurRepository;

import java.util.List;
@Transactional
@Service
public class EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    private TuteurRepository tuteurRepository;
    private StageRepository stageRepository;


    public EntrepriseService(EntrepriseRepository entrepriseRepository, TuteurRepository tuteurRepository, StageRepository stageRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.tuteurRepository = tuteurRepository;
        this.stageRepository = stageRepository;
    }

    public List<Entreprise> getEntreprises(){
        return entrepriseRepository.findAll();
    }
    public Entreprise getEntrepriseById(long id) throws ElementNotFoundException {
        if (entrepriseRepository.existsById(id))
            return entrepriseRepository.findById(id).get();
        else throw new ElementNotFoundException("L'entreprise n'existe pas!");
    }

    public Entreprise addEntreprise(Entreprise entreprise) throws IdAlreadyExistsException {
        if (entrepriseRepository.existsById(entreprise.getId())) throw new IdAlreadyExistsException("Le numéro de Siret existe déjà!");
        return entrepriseRepository.save(entreprise);
    }

    public void deleteEntreprise(long id) throws ElementNotFoundException {
        if (entrepriseRepository.existsById(id)){
            getEntrepriseById(id).getTuteurs().forEach(tuteur -> {
                tuteur.setEntreprise(null);
                tuteurRepository.save(tuteur);
            });
            getEntrepriseById(id).getStages().forEach(stage -> {
                stage.setEntreprise(null);
                stageRepository.save(stage);
            });
            entrepriseRepository.deleteById(id);
        }else{
            throw new ElementNotFoundException("L'entreprise n'existe pas!");
        }
    }

    public Entreprise updateEntreprise(long id, Entreprise e) throws ElementNotFoundException {
        Entreprise entreprise = getEntrepriseById(id);
        entreprise.setAdresse(e.getAdresse());
        entreprise.setContact(e.getContact());
        entreprise.setFomeJuridique(e.getFomeJuridique());
        entreprise.setTelContact(e.getTelContact());
        entreprise.setTelStandard(e.getTelStandard());
        entreprise.setRaisonSociale(e.getRaisonSociale());
        entreprise.setCodePostal(e.getCodePostal());
        return entrepriseRepository.save(entreprise);
    }





}
