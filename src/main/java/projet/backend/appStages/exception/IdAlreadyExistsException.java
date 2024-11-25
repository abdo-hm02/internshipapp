package projet.backend.appStages.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdAlreadyExistsException extends Exception {
    public IdAlreadyExistsException(String s) {
        super((s));
    }
}
