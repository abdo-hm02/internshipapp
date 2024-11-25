package projet.backend.appStages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "type_compétence")
public class TypeExigeCompetence {

    @Id
    @Column(name = "num_type_compétence")
    private TypeCompetenceId id;
    @ManyToOne
    @JoinColumn(name = "num_type")
    private TypeStage typeStage;
    @ManyToOne
    @JoinColumn(name = "num_compétence")
    private Competence competence;
    @Column(name = "niveau_exigé")
    private int niveauExige;
}
