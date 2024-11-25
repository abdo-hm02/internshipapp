package projet.backend.appStages.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.entity.Stage;
import projet.backend.appStages.entity.TypeStage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.DateStageRepository;
import projet.backend.appStages.repository.StageRepository;
import projet.backend.appStages.repository.TypeExigeCompetenceRepository;
import projet.backend.appStages.repository.TypeStageRepository;

import java.util.List;
@Transactional
@Service
public class TypeStageService {
    private TypeStageRepository typeStageRepository;
    private TypeExigeCompetenceRepository typeExigeCompetenceRepository;
    private DateStageRepository dateStageRepository;
    private StageRepository stageRepository;

    public TypeStageService(TypeStageRepository typeStageRepository, TypeExigeCompetenceRepository typeExigeCompetenceRepository, DateStageRepository dateStageRepository, StageRepository stageRepository) {
        this.typeStageRepository = typeStageRepository;
        this.typeExigeCompetenceRepository = typeExigeCompetenceRepository;
        this.dateStageRepository = dateStageRepository;
        this.stageRepository = stageRepository;
    }

    public List<TypeStage> getTypes(){
        return typeStageRepository.findAll();
    }
    public TypeStage getTypeById(int id) throws ElementNotFoundException {
        if (typeStageRepository.existsById(id))
            return typeStageRepository.findById(id).get();
        else throw new ElementNotFoundException("Le type n'existe pas!");
    }

    public TypeStage addType(TypeStage typeStage) throws IdAlreadyExistsException {
        if (typeStageRepository.existsById(typeStage.getId())) throw new IdAlreadyExistsException("L'id est déjà pris!");
        return typeStageRepository.save(typeStage);
    }

    public void deleteType(int id) throws ElementNotFoundException {
        if (typeStageRepository.existsById(id)){
            dateStageRepository.deleteByTypeStageId(id);
            typeExigeCompetenceRepository.deleteByTypeStageId(id);
            for (Stage stage : getTypeById(id).getStages()) {
                stage.setTypeStage(null);
                stageRepository.save(stage);
            }
            typeStageRepository.deleteById(id);
        }else throw new ElementNotFoundException("Le type n'existe pas!");
    }

    public TypeStage updateType(int id, TypeStage t) throws ElementNotFoundException {
        TypeStage typeStage = getTypeById(id);
        typeStage.setNbreSemaines(t.getNbreSemaines());
        return typeStageRepository.save(typeStage);
    }



}
