package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.Tuteur;

import java.util.List;

@Repository
public interface TuteurRepository extends JpaRepository<Tuteur, Long> {
    boolean existsByNomAndPrenom(String nom, String prenom);
    Tuteur findByNomAndPrenom(String nom, String prenom);
    List<Tuteur> findByEntrepriseId(long id);
}
