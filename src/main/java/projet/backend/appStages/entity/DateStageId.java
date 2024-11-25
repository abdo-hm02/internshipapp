package projet.backend.appStages.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateStageId implements Serializable {
    private int annee;
    private int idTypeStage;
}
