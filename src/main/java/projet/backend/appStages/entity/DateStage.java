package projet.backend.appStages.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "dates_stages")
public class DateStage {
    @Id
    @Column(name = "num_type_année")
    private DateStageId id;
    @ManyToOne
    @JoinColumn(name = "année")
    private Annee annee;
    @ManyToOne
    @JoinColumn(name = "num_type")
    private TypeStage typeStage;
    @Column(name = "date_début")
    private LocalDate dateDebut;
    @Column(name = "date_fin")
    private LocalDate dateFin;


}
