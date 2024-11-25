package projet.backend.appStages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.backend.appStages.entity.Promotion;

import java.util.List;

@Repository
public interface PromoRepository extends JpaRepository<Promotion, Long> {
    public Promotion findByEtudiantsNomAndEtudiantsPrenom(String nom, String prenom);
    public List<Promotion> findByProfesseurId(long id);
    public void deleteByProfesseurId(long id);
}
