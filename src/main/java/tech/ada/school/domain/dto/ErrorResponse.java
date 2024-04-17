package tech.ada.school.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tech.ada.school.domain.exception.NotFoundException;

import java.util.Collection;

@Data
public class ErrorResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<ErrorMessage> errors;

    private ErrorResponse(String message) {this.message = message;}

    private ErrorResponse(String message, Collection<ErrorMessage> errors) {
        this.message = message;
        this.errors = errors;
    }

    public static ErrorResponse createFromException(NotFoundException ex) {
        String message = "No record of " + ex.getClazz().getSimpleName() + " found for id " + ex.getId();

        return new ErrorResponse(message);
    }

    public static ErrorResponse createFromException (MethodArgumentNotValidException ex) {
        var violations = ex
                .getFieldErrors()
                .stream()
                .map(cv -> new ErrorMessage(cv.getField(), cv.getDefaultMessage()))
                .toList();

        return new ErrorResponse("Validation errors", violations);
    }
}
