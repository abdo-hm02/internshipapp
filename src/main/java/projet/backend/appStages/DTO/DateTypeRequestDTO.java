package projet.backend.appStages.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import projet.backend.appStages.entity.Annee;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DateTypeRequestDTO implements Serializable {
    private Annee annee;
    private LocalDate dateDebut;
    private LocalDate dateFin;

}
