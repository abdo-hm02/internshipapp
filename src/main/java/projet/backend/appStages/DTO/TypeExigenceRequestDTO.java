package projet.backend.appStages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import projet.backend.appStages.entity.Competence;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TypeExigenceRequestDTO implements Serializable {
    private Competence competence;
    private int niveauExige;
}
