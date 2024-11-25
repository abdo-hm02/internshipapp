package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.DateStage;
import projet.backend.appStages.entity.DateStageId;

import java.util.List;

@Repository
public interface DateStageRepository extends JpaRepository<DateStage, DateStageId> {
    List<DateStage> deleteByTypeStageId(int id);
    List<DateStage> deleteByAnneeId(int id);
    List<DateStage> findByTypeStageId(int id);
}
