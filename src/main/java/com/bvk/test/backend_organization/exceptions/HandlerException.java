package com.bvk.test.backend_organization.exceptions;

import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import com.bvk.test.backend_organization.enums.ResponseCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class HandlerException {

    private static final Logger log = LoggerFactory.getLogger(HandlerException.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ResponseData<List<String>>> handleValidationException(
            MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        return ResponseEntity.badRequest().body(ResponseData.<List<String>>builder()
                .code(ResponseCode.VALIDATION_ERROR.getCode())
                .message(ResponseCode.VALIDATION_ERROR.getMessage())
                .data(errors)
                .build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ResponseEntity<ResponseData<HashMap>> handleAuthenticationException(
            ExpiredJwtException exception
    ) {
        HashMap<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ResponseData.<HashMap>builder()
                        .code(ResponseCode.JWT_EXPIRED.getCode())
                        .message(ResponseCode.JWT_EXPIRED.getMessage())
                        .data(error)
                        .build());
    }

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ResponseEntity<ResponseData<HashMap>> handleSignatureException(
            Exception exception
    ) {
        HashMap<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ResponseData.<HashMap>builder()
                        .code(ResponseCode.SIGNATURE_ERROR.getCode())
                        .message(ResponseCode.SIGNATURE_ERROR.getMessage())
                        .data(error)
                        .build());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseEntity<ResponseData<String>> handleThrowableException(
            Throwable exception
    ) {

        log.error("Error global handling exception -> {}", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseData.<String>builder()
                        .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                        .message(ResponseCode.INTERNAL_SERVER_ERROR.getMessage())
                        .data(null)
                        .build());
    }
}
