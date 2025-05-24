package com.api.medical_scheduling.config;

import com.api.medical_scheduling.dto.ErrorResponseDTO;
import com.api.medical_scheduling.exception.*;
import com.api.medical_scheduling.exception.InvalidLoginException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.security.SignatureException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandlerConfig {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerConfig.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDTO.builder()
                        .error("UNKNOWN_ERROR")
                        .message(exception.getMessage())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .build()
                );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(ExpiredJwtException ignored) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponseDTO.builder()
                        .error("TOKEN_EXPIRED")
                        .message("O token fornecido expirou")
                        .statusCode(HttpStatus.FORBIDDEN.value())
                        .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(BadCredentialsException ignored) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDTO.builder()
                        .error("INVALID_CREDENTIALS")
                        .message("Credenciais inválidas")
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build()
                );
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(SignatureException ignored) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .error("INVALID_TOKEN")
                        .message("O token fornecido é inválido")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build()
                );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoResourceFoundException(ResourceNotFoundException ignored) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponseDTO.builder()
                                .error("RESOURCE_NOT_FOUND")
                                .message("Recurso não encontrado")
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .build()
                );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoResourceFoundException(NoHandlerFoundException ignored) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponseDTO.builder()
                                .error("ROUTE_NOT_FOUND")
                                .message("Rota não encontrada")
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .error("VALIDATION_ERROR")
                        .message(errorMessage)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build());
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationException(InvalidLoginException exception){
        String errorMessage = Objects.requireNonNull(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDTO.builder()
                        .error("AUTHENTICATION_ERROR")
                        .message(errorMessage)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build());
    }

    @ExceptionHandler(InvalidResourceException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceException(InvalidResourceException exception){
        String errorMessage = Objects.requireNonNull(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponseDTO.builder()
                        .error("INVALID_RESOURCE")
                        .message(errorMessage)
                        .statusCode(HttpStatus.FORBIDDEN.value())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException exception) {
        String errorMessage = exception.getMessage();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponseDTO.builder()
                        .error("ACCESS_DENIED")
                        .message(errorMessage)
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build());
    }
}