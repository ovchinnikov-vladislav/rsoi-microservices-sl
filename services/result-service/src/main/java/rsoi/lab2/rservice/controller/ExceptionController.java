package rsoi.lab2.rservice.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import rsoi.lab2.rservice.exception.HttpNotFoundException;
import rsoi.lab2.rservice.exception.HttpNotValueOfParameterException;
import rsoi.lab2.rservice.model.ErrorResponse;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exc) {
        List<FieldError> errors = exc.getBindingResult().getFieldErrors();
        String result = errors.stream()
                .map(err -> String.format("Field %s has wrong value: [%s]", err.getField(), err.getDefaultMessage()))
                .collect(Collectors.joining("; "));
        logger.error("Bad Request: {}", result);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JdbcSQLException.class)
    @ResponseBody
    public ErrorResponse jdbcSqlExceptionHandler(JdbcSQLException exc) {
        logger.error("Bad Request: {}", exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public ErrorResponse invalidFormatExceptionHandler(InvalidFormatException exc) {
        logger.error("Bad Request: {}", exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpNotFoundException.class)
    @ResponseBody
    public ErrorResponse httpNotFoundExceptionHandler(HttpNotFoundException exc) {
        logger.error("Not Found: {}", exc.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ErrorResponse missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException exc) {
        logger.error("Bad Request: {}", exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpNotValueOfParameterException.class)
    @ResponseBody
    public ErrorResponse httpNotValueOfParameterException(HttpNotValueOfParameterException exc) {
        logger.error("Bad Request: {}", exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ErrorResponse constraintViolationExceptionHandler(ConstraintViolationException exc) {
        logger.error("Bad Request: {}", exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ErrorResponse methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exc) {
        logger.error("Bad Request: {}", exc.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exc.getMessage());
    }
}
