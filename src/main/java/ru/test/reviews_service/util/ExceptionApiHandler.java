package ru.test.reviews_service.util;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Обработчик исключений для REST-контроллеров.
 * Класс перехватывает исключения, возникающие в контроллерах, и возвращает соответствующие ответы клиенту.
 */
@RestControllerAdvice
public class ExceptionApiHandler {

    /**
     * Обрабатывает исключение EntityNotFoundException, возникающее при неуспешном поиске сущности,
     * и возвращает ответ с кодом 404 (NOT FOUND).
     *
     * @param e исключение, которое было выброшено.
     * @return ResponseEntity с сообщением об ошибке и статусом NOT FOUND.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Обрабатывает исключение MethodArgumentNotValidException, возникающее при нарушении валидации входных параметров,
     * и возвращает ответ с кодом 400 (BAD REQUEST).
     *
     * @param e исключение, которое было выброшено.
     * @return ResponseEntity с сообщением об ошибке и статусом BAD REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleEntityNotFound(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обрабатывает иные исключения и возвращает ответ с кодом 500 (INTERNAL SERVER ERROR).
     *
     * @param e исключение, которое было выброшено.
     * @return ResponseEntity с сообщением об ошибке и статусом INTERNAL SERVER ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
