package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.Professeur;
@Repository
public interface ProfRepository extends JpaRepository<Professeur, Long> {
    Professeur findByNomAndPrenom(String nom, String prenom);
    Boolean existsByNomAndPrenom(String nom, String prenom);
}
