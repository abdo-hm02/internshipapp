package projet.backend.appStages.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.entity.Annee;
import projet.backend.appStages.entity.Stage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.AnneeRepository;
import projet.backend.appStages.repository.DateStageRepository;
import projet.backend.appStages.repository.StageRepository;

import java.util.HashSet;
import java.util.List;

@Transactional
@Service
public class AnneeService {

    private AnneeRepository anneeRepository;
    private DateStageRepository dateStageRepository;
    private StageRepository stageRepository;

    public AnneeService(AnneeRepository anneeRepository, DateStageRepository dateStageRepository, StageRepository stageRepository) {
        this.anneeRepository = anneeRepository;
        this.dateStageRepository = dateStageRepository;
        this.stageRepository = stageRepository;
    }

    public List<Annee> getAnnees(){
        return anneeRepository.findAll();
    }

    public Annee getAnnee(int annee) throws ElementNotFoundException {
        return anneeRepository.findById(annee).orElseThrow(()->new ElementNotFoundException("L'année n'existe pas!"));
    }

    public Annee addAnnee(int annee) throws IdAlreadyExistsException {
        if (anneeRepository.existsById(annee)) throw new IdAlreadyExistsException("L'année est déjà enregistrée!");
        Annee annee1 = new Annee(annee, new HashSet<>(), new HashSet<>());
        return anneeRepository.save(annee1);
    }

    public void deleteAnnee(int annee) throws ElementNotFoundException {
        if (anneeRepository.existsById(annee)) {
            dateStageRepository.deleteByAnneeId(annee);
            for (Stage stage : getAnnee(annee).getStages()) {
                stage.setAnnee(null);
                stageRepository.save(stage);
            }
            anneeRepository.deleteById(annee);
        }else  throw new ElementNotFoundException("L'année n'existe pas!") ;
    }







}
