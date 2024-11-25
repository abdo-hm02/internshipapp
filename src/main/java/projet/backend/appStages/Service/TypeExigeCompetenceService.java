package projet.backend.appStages.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.DTO.TypeExigenceRequestDTO;
import projet.backend.appStages.entity.Competence;
import projet.backend.appStages.entity.TypeCompetenceId;
import projet.backend.appStages.entity.TypeExigeCompetence;
import projet.backend.appStages.entity.TypeStage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.repository.TypeExigeCompetenceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional
@Service
public class TypeExigeCompetenceService {
    private TypeExigeCompetenceRepository typeExigeCompetenceRepository;
    private TypeStageService typeStageService;
    private CompetenceService competenceService;
    


    public TypeExigeCompetenceService(TypeExigeCompetenceRepository typeExigeCompetenceRepository, TypeStageService typeStageService, CompetenceService competenceService) {
        this.typeExigeCompetenceRepository = typeExigeCompetenceRepository;
        this.typeStageService = typeStageService;
        this.competenceService = competenceService;
    }

    public TypeExigenceRequestDTO convertToDTO(TypeExigeCompetence typeExigeCompetence){
        TypeExigenceRequestDTO dto = new TypeExigenceRequestDTO();
        dto.setCompetence(typeExigeCompetence.getCompetence());
        dto.setNiveauExige(typeExigeCompetence.getNiveauExige());
        return dto;
    }

    public List<TypeExigeCompetence> getExigences(){
        return typeExigeCompetenceRepository.findAll();
    }

    public Map<String, Integer> getExigenceById(int idType, int idCompetence) throws ElementNotFoundException {
        Map<String, Integer> map = new HashMap<>();
        TypeCompetenceId id = new TypeCompetenceId(idType,idCompetence);
        if (typeExigeCompetenceRepository.existsById(id)) {
            TypeExigeCompetence exigence = typeExigeCompetenceRepository.findById(id).get();
            map.put("niveauExigé", exigence.getNiveauExige());
            return map;
        }else throw new ElementNotFoundException("Ce type n'exige pas cette compétence!");
    }

    public TypeExigeCompetence addCompetenceToType(int idType, int idCompetence, int niveau) throws ElementNotFoundException {
        TypeStage typeStage = typeStageService.getTypeById(idType);
        Competence competence = competenceService.getCompetenceById(idCompetence);
        TypeCompetenceId id = new TypeCompetenceId(idType, idCompetence);
        TypeExigeCompetence typeExigeCompetence = new TypeExigeCompetence(id, typeStage, competence, niveau);
        return typeExigeCompetenceRepository.save(typeExigeCompetence);
    }

    public void deleteExigence(int idType, int idCompetence) throws ElementNotFoundException {
        TypeCompetenceId id = new TypeCompetenceId(idType, idCompetence);
        if (typeExigeCompetenceRepository.existsById(id))
            typeExigeCompetenceRepository.deleteById(id);
        else throw new ElementNotFoundException("Ce type n'exige pas cette compétence!");
    }

    public List<TypeExigenceRequestDTO> getCompetencesByType(int idT){
        List<TypeExigeCompetence> typeExigeCompetenceliste = typeExigeCompetenceRepository.findByTypeStageId(idT);
        List<TypeExigenceRequestDTO> liste = new ArrayList<>();
        typeExigeCompetenceliste.forEach(ex->{
            liste.add(convertToDTO(ex));
        });
        return liste;
    }

    public TypeExigeCompetence updateExigence(int idT, int idC, int niv) throws ElementNotFoundException {
        TypeCompetenceId id = new TypeCompetenceId(idT, idC);
        if (typeExigeCompetenceRepository.existsById(id)) {
            TypeExigeCompetence typeExigeCompetence = typeExigeCompetenceRepository.findById(id).get();
            typeExigeCompetence.setNiveauExige(niv);
            return typeExigeCompetenceRepository.save(typeExigeCompetence);
        }else return addCompetenceToType(idT, idC, niv);

    }







}
