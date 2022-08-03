package com.wilmardeml.springboot.backend.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class ArchivoUploadException {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxSizeException(MaxUploadSizeExceededException e) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Error al subir la imagen el tamaño máximo son 10MB");
        respuesta.put("error", Objects.requireNonNull(e.getMessage()).concat(": ".concat(e.getMostSpecificCause().getMessage())));
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
