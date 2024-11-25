package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.TypeStage;
@Repository
public interface TypeStageRepository extends JpaRepository<TypeStage, Integer> {

}
