package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Type_de_stage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TypeStage {

    @Id
    @Column(name = "num_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nbre_semaines")
    private int nbreSemaines;

    @OneToMany(mappedBy = "typeStage", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TypeExigeCompetence> typeExigeCompetencesSet;

    @OneToMany(mappedBy = "typeStage", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DateStage> dateStageSet = new HashSet<>();

    @OneToMany(mappedBy = "typeStage")
    @JsonIgnore
    private Set<Stage> stages = new HashSet<>();


}
