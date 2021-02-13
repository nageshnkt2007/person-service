package com.service.person.aop;

import com.service.person.dto.ApiError;
import com.service.person.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


@RestControllerAdvice
@Component
public class PeopleServiceExceptionHandler {

  private final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

  /**
   * handle controller methods parameter validation exceptions
   *
   * @param exception ex
   * @return ResponseEntity with appropriate htttp status
   */
  @ExceptionHandler
  public ResponseEntity<?> handle(MethodArgumentNotValidException exception) {
    ApiError apiError = new ApiError();
    apiError.setStatus(HttpStatus.PRECONDITION_FAILED.value());
    apiError.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
    apiError.setMessage(
            exception.getAllErrors().get(0).getDefaultMessage());
    return new ResponseEntity<>(apiError, HttpStatus.PRECONDITION_FAILED);
  }

  /**
   * handle invalid person data in update requests.
   *
   * @param exception ex
   * @return ResponseEntity with appropriate htttp status
   */
  @ExceptionHandler
  public ResponseEntity<?> handle(BadRequestException exception) {
    ApiError apiError = new ApiError();
    apiError.setStatus(HttpStatus.BAD_REQUEST.value());
    apiError.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
    apiError.setMessage(exception.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }


  /**
   * handle controller methods parameter validation exceptions
   *
   * @param exception ex
   * @return wrapped result
   */
  @ExceptionHandler
  public ResponseEntity<?> handle(ConstraintViolationException exception) {

    Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
    LOG.error("Validation exception occured :: {}", exception);
    StringBuilder builder = new StringBuilder();
    for (ConstraintViolation<?> violation : violations) {
      builder.append(violation.getMessage());
      break;
    }
    ApiError apiError = new ApiError();
    apiError.setStatus(HttpStatus.PRECONDITION_FAILED.value());
    apiError.setError(HttpStatus.PRECONDITION_FAILED.getReasonPhrase());
    apiError.setMessage(
            builder.toString());
    return new ResponseEntity<>(apiError, HttpStatus.PRECONDITION_FAILED);
  }

}
