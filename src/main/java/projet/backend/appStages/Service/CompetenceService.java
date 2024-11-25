package projet.backend.appStages.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.backend.appStages.entity.Competence;
import projet.backend.appStages.exception.ElementNotFoundException;
import projet.backend.appStages.repository.CompetenceRepository;
import projet.backend.appStages.repository.TypeExigeCompetenceRepository;

import java.util.List;
@Transactional
@Service
public class CompetenceService {
    private CompetenceRepository competenceRepository;
    private TypeExigeCompetenceRepository typeExigeCompetenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository, TypeExigeCompetenceRepository typeExigeCompetenceRepository) {
        this.competenceRepository = competenceRepository;
        this.typeExigeCompetenceRepository = typeExigeCompetenceRepository;
    }

    public List<Competence> getCompetences(){
        return competenceRepository.findAll();
    }
    public Competence getCompetenceById(int id) throws ElementNotFoundException {
        if(competenceRepository.existsById(id))
            return competenceRepository.findById(id).get();
        else throw new ElementNotFoundException("La compétence n'existe pas!");
    }

    public Competence addCompetence(Competence competence){
        return competenceRepository.save(competence);
    }

    public void deleteCompetence(int id) throws ElementNotFoundException {
        if (competenceRepository.existsById(id)) {
            typeExigeCompetenceRepository.deleteByCompetenceId(id);
            competenceRepository.deleteById(id);
        }else throw new ElementNotFoundException("La compétence n'existe pas!");
    }

    public Competence updateCompetence(int id, Competence c) throws ElementNotFoundException {
        Competence competence = getCompetenceById(id);
        competence.setLibelle(c.getLibelle());
        competence.setDescription(c.getDescription());
        return competenceRepository.save(competence);
    }

}

