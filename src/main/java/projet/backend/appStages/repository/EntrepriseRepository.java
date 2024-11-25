package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.Entreprise;
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {





}
