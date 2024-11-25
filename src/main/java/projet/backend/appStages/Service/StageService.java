package projet.backend.appStages.Service;

import jakarta.el.ELException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.DTO.StageRequestDTO;
import projet.backend.appStages.entity.Stage;
import projet.backend.appStages.entity.TypeStage;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.exception.IdAlreadyExistsException;
import projet.backend.appStages.repository.StageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StageService {

    private StageRepository stageRepository;
    private AnneeService anneeService;
    private EtudiantService etudiantService;
    private EntrepriseService entrepriseService;
    private TuteurService tuteurService;
    private ProfService profService;
    private TypeStageService typeStageService;


    public StageService(StageRepository stageRepository, AnneeService anneeService, EtudiantService etudiantService, EntrepriseService entrepriseService, TuteurService tuteurService, ProfService profService, TypeStageService typeStageService) {
        this.stageRepository = stageRepository;
        this.anneeService = anneeService;
        this.etudiantService = etudiantService;
        this.entrepriseService = entrepriseService;
        this.tuteurService = tuteurService;
        this.profService = profService;
        this.typeStageService = typeStageService;
    }

    public StageRequestDTO convertToDTO(Stage stage){
        StageRequestDTO dto = new StageRequestDTO();
        dto.setId(stage.getId());
        dto.setCompteRendu(stage.getCompteRendu());
        if(stage.getAnnee()!=null) dto.setAnnee(stage.getAnnee().getId());
        if(stage.getProfesseur()!=null) dto.setIdProf(stage.getProfesseur().getId());
        if(stage.getEntreprise()!=null) dto.setIdEntreprise(stage.getEntreprise().getId());
        if(stage.getEtudiant()!=null) dto.setIdEtudiant(stage.getEtudiant().getId());
        if(stage.getTuteur()!=null) dto.setIdTuteur(stage.getTuteur().getId());
        if(stage.getTypeStage()!=null) dto.setIdType(stage.getTypeStage().getId());
        return dto;
    }
    
    public List<Stage> getInternships(){
    	List<Stage> stages = stageRepository.findAll();
    	return stages;
    }

    public List<StageRequestDTO> getStages(){
        List<Stage> stages = stageRepository.findAll();
        List<StageRequestDTO> liste = new ArrayList<>();
        stages.forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }

    public StageRequestDTO getStageById(long id) throws ElementNotFoundException {
        if (stageRepository.existsById(id)){
            return convertToDTO(stageRepository.findById(id).get());
        }else{
            throw new ElementNotFoundException("Le stage n'existe pas!");
        }
    }

    public StageRequestDTO addStage(StageRequestDTO dto) throws ElementNotFoundException {
        Stage stage = new Stage();
        stage.setCompteRendu(dto.getCompteRendu());
        if (dto.getAnnee()!=0) stage.setAnnee(anneeService.getAnnee(dto.getAnnee()));
        if (dto.getIdEtudiant()!=0) stage.setEtudiant(etudiantService.getEtudiantById(dto.getIdEtudiant()));
        if(dto.getIdEntreprise()!=0) stage.setEntreprise(entrepriseService.getEntrepriseById(dto.getIdEntreprise()));
        if(dto.getIdProf()!=0) stage.setProfesseur(profService.getProfById(dto.getIdProf()));
        if(dto.getIdTuteur()!=0) stage.setTuteur(tuteurService.getTuteurById(dto.getIdTuteur()));
        if(dto.getIdType()!=0) stage.setTypeStage(typeStageService.getTypeById(dto.getIdType()));
        return convertToDTO(stageRepository.save(stage));
    }

    public void deleteStage(long id) throws ElementNotFoundException {
        if (stageRepository.existsById(id))
            stageRepository.deleteById(id);
        else throw  new ElementNotFoundException("Le stage n'existe pas");
    }

    public StageRequestDTO updateStage(long id, StageRequestDTO dto) throws ElementNotFoundException {
        Stage stage = stageRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Le stage n'existe pas"));
        stage.setCompteRendu(dto.getCompteRendu());
        if(dto.getAnnee()!=0) stage.setAnnee(anneeService.getAnnee(dto.getAnnee()));
        if(dto.getIdEtudiant()!=0) stage.setEtudiant(etudiantService.getEtudiantById(dto.getIdEtudiant()));
        if(dto.getIdEntreprise()!=0) stage.setEntreprise(entrepriseService.getEntrepriseById(dto.getIdEntreprise()));
        if(dto.getIdProf()!=0) stage.setProfesseur(profService.getProfById(dto.getIdProf()));
        if(dto.getIdTuteur()!=0) stage.setTuteur(tuteurService.getTuteurById(dto.getIdTuteur()));
        if(dto.getIdType()!=0) stage.setTypeStage(typeStageService.getTypeById(dto.getIdType()));
        return convertToDTO(stageRepository.save(stage));
    }

    public List<StageRequestDTO> getStagesByEtudiant(long id){
        List<StageRequestDTO> liste = new ArrayList<>();
        stageRepository.findByEtudiantId(id).forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }
    public List<StageRequestDTO> getStagesByEntreprise(long id){
        List<StageRequestDTO> liste = new ArrayList<>();
        stageRepository.findByEntrepriseId(id).forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }

    public List<StageRequestDTO> getStagesByProf(long id){
        List<StageRequestDTO> liste = new ArrayList<>();
        stageRepository.findByProfesseurId(id).forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }


    public List<StageRequestDTO> getStagesByTuteur(long id){
        List<StageRequestDTO> liste = new ArrayList<>();
        stageRepository.findByTuteurId(id).forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }

    public List<StageRequestDTO> getStagesByAnnee(int id){
        List<StageRequestDTO> liste = new ArrayList<>();
        stageRepository.findByAnneeId(id).forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }

    public List<StageRequestDTO> getStagesByType(int id){
        List<StageRequestDTO> liste = new ArrayList<>();
        stageRepository.findByTypeStageId(id).forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }


    public List<StageRequestDTO> getStagesByEtudiantAndType(long idE, int idT){
        List<StageRequestDTO> liste = new ArrayList<>();
        stageRepository.findByEtudiantIdAndTypeStageId(idE, idT).forEach(stage -> {
            liste.add(convertToDTO(stage));
        });
        return liste;
    }






}
