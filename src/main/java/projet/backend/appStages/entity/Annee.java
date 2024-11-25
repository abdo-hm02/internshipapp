package projet.backend.appStages.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Année")
public class Annee {
    @Id
    @Column(name="année", nullable = false)
    @Min(value = 0, message = "L'annéen'est pas valide")
    private int id;

    @OneToMany(mappedBy = "annee", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DateStage> dateStageSet = new HashSet<>();

    @OneToMany(mappedBy = "annee")
    @JsonIgnore
    private Set<Stage> stages = new HashSet<>();
}
