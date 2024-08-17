package com.bvk.test.backend_organization.exceptions;

import com.bvk.test.backend_organization.dtos.responses.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ResponseData<List<String>>> handleValidationException(
            MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        return ResponseEntity.badRequest().body(ResponseData.<List<String>>builder()
                .message("Validation Failed")
                .data(errors)
                .build());
    }

}
