package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Etudiant")
public class Etudiant {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_etudiant")
    private long id;
    @Column(name = "nom_etudiant", nullable = false)
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le prénom est obligatoire")
    @Column(name = "prenom_etudiant", nullable = false)
    private String prenom;
    @Column(name = "qualité_etudiant")
    private String qualite;
    @Column(name = "adresse_etudiant")
    //@Length(min=5, max=70, message = "adresse de longueur non valide!")
    private String adresse;
    @Column(name = "code_postal_etudiant")
    private int codePostal;
    @Column(name = "ddn_etudiant")
    private LocalDate ddn;
    @Column(name = "sexe_etudiant")
    private String sexe;
    @Column(name = "mention_etudiant")
    private String mention;
    @Column(name = "ville_etudiant")
    private String ville;
    @Column(name = "tél_etudiant", length = 10)
    //@Pattern(regexp = "^06.{8}", message = "Numéro non valide!")
    private String numTel;
    @ManyToOne()
    @JoinColumn(name="année_promotion" ,nullable=true)
    private Promotion promo;
    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    private Set<Stage> stages = new HashSet<>();

}
