package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.Competence;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
}
