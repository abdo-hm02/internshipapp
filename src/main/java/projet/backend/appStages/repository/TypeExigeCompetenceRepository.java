package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.TypeCompetenceId;
import projet.backend.appStages.entity.TypeExigeCompetence;

import java.util.List;

@Repository
public interface TypeExigeCompetenceRepository extends JpaRepository<TypeExigeCompetence, TypeCompetenceId> {
    List<TypeExigeCompetence> findByTypeStageId(int id);
    List<TypeExigeCompetence> findByCompetenceId(int id);
    void deleteByTypeStageId(int id);
    void deleteByCompetenceId(int id);

}
