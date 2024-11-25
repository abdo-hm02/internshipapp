package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Professeur")
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num_prof")
    private long id;
    @Column(name="qualité_prof")
    private String qualite;
    @Column(name="nom_prof", nullable = false)
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @Column(name="prénom_prof", nullable = false)
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    @Column(name="adresse_prof")
    //@Length(min= 5, max=70, message = "adresse de longueur non valide!")
    private String adresse;
    @Column(name = "code_postal_prof")
    private int codePostal;
    @Column(name = "ville_prof")
    private String ville;
    @Column(name = "tél_école")
    //@Pattern(regexp = "^06.{8}", message = "Numéro non valide!")
    private String telEcole;
    @Column(name = "tél_domicile")
    //@Pattern(regexp = "^06.{8}", message = "Numéro non valide!")
    private String telDomicile;
    @Column(name="date_embauche")
    private LocalDate dateEmbauche;
    @Column(name="date_départ")
    private LocalDate dateDepart;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Promotion> promotionSet = new HashSet<>();
    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Stage> stages = new HashSet<>();

}