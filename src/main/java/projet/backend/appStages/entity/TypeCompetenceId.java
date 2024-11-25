package projet.backend.appStages.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TypeCompetenceId implements Serializable {
    private int typeId;
    private  int competenceId;
}
