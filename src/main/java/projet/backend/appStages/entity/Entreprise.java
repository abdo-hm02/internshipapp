package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Entreprise")
public class Entreprise {
    @Id
    @Column(name="num_siret")
    private long id;
    @Column(name="forme_juridique")
    private String fomeJuridique;
    @Column(name = "raison_sociale")
    private String raisonSociale;
    @Column(name = "adresse_entreprise")
    private String adresse;
    @Column(name="code_postal_entreprise")
    private int codePostal;
    @Column(name="ville_entreprise")
    private String ville;
    @Column(name="tél_standard")
    //@Pattern(regexp = "^06.{8}", message = "Numéro non valide!")
    private String telStandard;
    @Column(name="contact")
    private String contact;
    //@Pattern(regexp = "^06.{8}", message = "Numéro non valide!")
    @Column(name = "tél_contact")
    private String telContact;
    @JsonIgnore
    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private Set<Tuteur> tuteurs = new HashSet<>();
    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    private Set<Stage> stages = new HashSet<>();
}
