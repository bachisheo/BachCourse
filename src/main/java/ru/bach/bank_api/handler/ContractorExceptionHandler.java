package ru.bach.bank_api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.bach.bank_service.exception.ContractorNotFoundException;

/**
 * Обработка исключений
 */
@ControllerAdvice
public class ContractorExceptionHandler {

        @ExceptionHandler(value = ContractorNotFoundException.class)
        public ResponseEntity<Object> resourceNotFound(ContractorNotFoundException exception) {
            return new ResponseEntity<>("Contractor: " + exception.getMessage(), HttpStatus.NOT_FOUND);
        }


    }
