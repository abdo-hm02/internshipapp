package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.Annee;
@Repository
public interface AnneeRepository extends JpaRepository<Annee, Integer> {
}
