package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "compétence")
public class Competence {
    @Id
    @Column(name = "num_compétence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "libellé")
    @NotNull(message = "le libellé est obligatoire")
    private String libelle;
    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TypeExigeCompetence> typeExigeCompetenceSet = new HashSet<>();


}
