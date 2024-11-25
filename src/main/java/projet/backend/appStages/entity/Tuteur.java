package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tuteur")
public class Tuteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_tuteur")
    private long id;
    @Column(name = "qualité_prof")
    private String qualite;
    @Column(name = "nom_tuteur")
    @NotBlank(message = "Le nom est obligatoire!")
    @NotNull(message = "Le nom est obligatoire!")
    private String nom;
    @Column(name = "prénom_tuteur")
    @NotBlank(message = "Le prénom est obligatoire!")
    @NotNull(message = "Le prénom est obligatoire!")
    private String prenom;
    @Column(name="tél_tuteur")
    private String tel;
    @ManyToOne
    @JoinColumn(name ="num_entreprise")
    private Entreprise entreprise;
    @OneToMany(mappedBy = "tuteur")
    @JsonIgnore
    private Set<Stage> stages = new HashSet<>();

}
