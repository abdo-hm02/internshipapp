package projet.backend.appStages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "compte_rendu")
    private String compteRendu;
    @ManyToOne
    @JoinColumn(name = "num_étudiant")
    private Etudiant etudiant;
    @ManyToOne
    @JoinColumn(name = "num_prof", nullable = true)
    private Professeur professeur;
    @ManyToOne
    @JoinColumn(name="num_entreprise", nullable = true)
    private Entreprise entreprise;
    @ManyToOne
    @JoinColumn(name="num_tuteur", nullable = true)
    private Tuteur tuteur;
    @ManyToOne
    @JoinColumn(name="année", nullable = true)
    private Annee annee;
    @ManyToOne
    @JoinColumn(name="num_type", nullable = true)
    private TypeStage typeStage;

}
