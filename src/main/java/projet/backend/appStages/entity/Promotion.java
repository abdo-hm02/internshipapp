package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Table(name="Promotion")
public class Promotion {
    @Id
    @Column(name="année_promo", nullable = false)
    private long id;
    @Column(name = "nbre_inscrits")
    private long nbreInscrits;
    @Column(name = "nbre_recus")
    private long nbreRecus;
    
    @JsonIgnore
    @OneToMany(mappedBy = "promo")
    private Set<Etudiant> etudiants = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name="prof_responsable", nullable = true)
    private Professeur professeur;

}
