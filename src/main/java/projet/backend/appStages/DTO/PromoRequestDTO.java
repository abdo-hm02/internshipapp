package projet.backend.appStages.DTO;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Valid
public class PromoRequestDTO implements Serializable {
    private long id;
    private long nbreInscrits;
    private long nbreRecus;
    private long idProf;
}
