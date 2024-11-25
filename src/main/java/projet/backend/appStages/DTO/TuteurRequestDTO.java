package projet.backend.appStages.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.backend.appStages.entity.Entreprise;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TuteurRequestDTO implements Serializable {

    private String qualite;
    private String nom;
    private String prenom;
    private String tel;
    private long idEntreprise;

}
