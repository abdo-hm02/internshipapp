package projet.backend.appStages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StageRequestDTO implements Serializable {
    private long id;
    private String compteRendu;
    private long idEtudiant;
    private long idProf;
    private long idEntreprise;
    private long idTuteur;
    private int annee;
    private int idType;
}
