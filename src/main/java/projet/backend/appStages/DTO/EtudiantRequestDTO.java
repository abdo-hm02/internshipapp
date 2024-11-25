package projet.backend.appStages.DTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EtudiantRequestDTO {
    private long id;
    private String nom;
    private String prenom;
    private String qualite;
    private String adresse;
    private int codePostal;
    private LocalDate ddn;
    private String sexe;
    private String mention;
    private String ville;
    private String numTel;
    private long idPromo;
}
