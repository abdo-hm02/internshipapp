package projet.backend.appStages.repository;

import org.aspectj.weaver.ast.Literal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.Stage;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByEtudiantId(long id);
    List<Stage> findByEntrepriseId(long id);
    List<Stage> findByProfesseurId(long id);
    List<Stage> findByTuteurId(long id);
    List<Stage> findByTypeStageId(int id);
    List<Stage> findByAnneeId(int id);
    List<Stage> findByEtudiantIdAndTypeStageId(long idE, int idT);
    void deleteByEtudiantId(long id);
}
