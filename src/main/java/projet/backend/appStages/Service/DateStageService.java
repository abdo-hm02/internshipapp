package projet.backend.appStages.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.DTO.DateStageDTO;
import projet.backend.appStages.DTO.DateTypeRequestDTO;
import projet.backend.appStages.entity.Annee;
import projet.backend.appStages.entity.DateStage;
import projet.backend.appStages.entity.DateStageId;
import projet.backend.appStages.entity.TypeStage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.AnneeRepository;
import projet.backend.appStages.repository.DateStageRepository;
import projet.backend.appStages.repository.TypeStageRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DateStageService {
    private DateStageRepository dateStageRepository;
    private AnneeRepository anneeRepository;
    private TypeStageRepository typeStageRepository;

    public DateStageService(DateStageRepository dateStageRepository, AnneeRepository anneeRepository, TypeStageRepository typeStageRepository) {
        this.dateStageRepository = dateStageRepository;
        this.anneeRepository = anneeRepository;
        this.typeStageRepository = typeStageRepository;
    }

    public DateStageDTO convertToDTO(DateStage dateStage){
        DateStageDTO dto = new DateStageDTO();
        dto.setDateDebut(dateStage.getDateDebut());
        dto.setDateFin(dateStage.getDateFin());
        return dto;
    }

    public DateStageDTO getDateById(int annee, int idType) throws ElementNotFoundException {
        DateStageId id = new DateStageId(annee, idType);
        if (dateStageRepository.existsById(id)){
            DateStage dateStage = dateStageRepository.findById(id).get();
            return convertToDTO(dateStage);
        }else{
            throw new ElementNotFoundException("Info non disponible");
        }
    }

    public List<DateStage> getDates(){
        return dateStageRepository.findAll();
    }

    public DateStage addDateStage(int annee, int idType, DateStageDTO dateStageDTO) throws IdAlreadyExistsException, ElementNotFoundException {
        DateStageId id = new DateStageId(annee, idType);
        if (dateStageRepository.existsById(id)){
            throw new IdAlreadyExistsException("Cette information existe déjà!");
        }else{
              Annee annee1 = anneeRepository.findById(annee).orElseThrow(() -> new ElementNotFoundException("L'année n'existe pas"));
              TypeStage typeStage = typeStageRepository.findById(idType).orElseThrow(() -> new ElementNotFoundException("Le type n'existe pas"));
              DateStage dateStage = new DateStage(id, annee1, typeStage, dateStageDTO.getDateDebut(), dateStageDTO.getDateFin());
              dateStage.setDateFin(dateStageDTO.getDateFin());
              return dateStageRepository.save(dateStage);
        }
    }


    public DateStage updateDateStage(int annee, int idType, DateStageDTO dateStageDTO) throws ElementNotFoundException {
        DateStageId id = new DateStageId(annee, idType);
        DateStage dateStage = dateStageRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Cette information n'existe pas!"));
        dateStage.setDateDebut(dateStageDTO.getDateDebut());
        dateStage.setDateFin(dateStageDTO.getDateFin());
        return dateStageRepository.save(dateStage);
    }


    public void deleteDateStage(int annee, int idType) throws ElementNotFoundException {
        DateStageId id = new DateStageId(annee, idType);
        if (dateStageRepository.existsById(id)) dateStageRepository.deleteById(id);
        else throw new ElementNotFoundException("Cette information n'existe pas!");
    }

    public List<DateTypeRequestDTO> getDatesByType(int idT){
        List<DateStage> dates = dateStageRepository.findByTypeStageId(idT);
        List<DateTypeRequestDTO> liste = new ArrayList<>();
        dates.forEach(dateStage -> {
            DateTypeRequestDTO dto = new DateTypeRequestDTO(dateStage.getAnnee(), dateStage.getDateDebut(), dateStage.getDateFin());
            liste.add(dto);
        });
        return liste;
    }



}
