package cat.linky.linky_cat_api.infra.exception;

import java.time.Instant;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.ExceptionResponse;
import cat.linky.linky_cat_api.core.exception.ApplicationException;
import cat.linky.linky_cat_api.core.exception.IntegrityViolationException;
import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;
import cat.linky.linky_cat_api.core.exception.InvalidCredentialsException;
import cat.linky.linky_cat_api.core.exception.ResourceNotFoundException;
import cat.linky.linky_cat_api.core.exception.TooManyRequestsException;
import cat.linky.linky_cat_api.core.exception.UnauthorizedOperationException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    public ControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ExceptionResponse> invalidArgument(InvalidArgumentException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;        
    
        ExceptionResponse res = buildResponse(
            "Invalid argument", 
            status, 
            exception, 
            request
        );

        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(IntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> integrityViolation(IntegrityViolationException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;        

        ExceptionResponse res = buildResponse(
            "Integrity violation", 
            status, 
            exception, 
            request
        );

        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; 

        ExceptionResponse res = buildResponse(
            "Resource not found", 
            status, 
            exception, 
            request
        );

        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidCredentials(InvalidCredentialsException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; 

        ExceptionResponse res = buildResponse(
            "Invalid credentials", 
            status, 
            exception, 
            request
        );

        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedOperation(UnauthorizedOperationException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED; 
        
        ExceptionResponse res = buildResponse(
            "Unauthorized operation", status, 
            exception, 
            request
        );

        return ResponseEntity.status(status).body(res);
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<ExceptionResponse> tooManyRequests(TooManyRequestsException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.TOO_MANY_REQUESTS; 
        
        ExceptionResponse res = buildResponse(
            "Too many requests", 
            status, 
            exception, 
            request
        );

        return ResponseEntity.status(status).body(res);
    }

    private ExceptionResponse buildResponse(String error, HttpStatus status, ApplicationException exception, HttpServletRequest request) {
        return new ExceptionResponse(
            Instant.now(),
            status.value(),
            error,
            exception.getCode(),
            exceptionMessage(exception, request.getLocale()),
            request.getRequestURI()
        ); 
    }

    private String exceptionMessage(ApplicationException exception, Locale locale) {
        return messageSource.getMessage(
            exception.getCode(), 
            exception.getArgs(), 
            locale
        );
    }
}
