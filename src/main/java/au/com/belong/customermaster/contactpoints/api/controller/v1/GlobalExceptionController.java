package au.com.belong.customermaster.contactpoints.api.controller.v1;

import au.com.belong.customermaster.contactpoints.exception.ResourceNotFoundException;
import au.com.belong.customermaster.contactpoints.model.error.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiError> resourceNotFoundViolationException(HttpServletResponse response, HttpServletRequest httpServletRequest, Throwable t) throws IOException {
        logException(t, httpServletRequest, HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity<>(ApiError.builder().errorId(HttpStatus.BAD_REQUEST.toString())
                .errorMessage(t.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class })
    public ResponseEntity<List<ApiError>> constraintViolationException(HttpServletResponse response, HttpServletRequest httpServletRequest, Throwable t) throws IOException {
        logException(t, httpServletRequest, HttpStatus.BAD_REQUEST.toString());
        MethodArgumentNotValidException m = (MethodArgumentNotValidException) t;
        List<ApiError> apiErrors = processFieldErrors(m.getFieldErrors());
        log.error("Api Error : {}", apiErrors);

        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);

    }

    private List<ApiError> processFieldErrors(List<FieldError> fieldErrors) {
        List<ApiError> errorsList = new ArrayList<>();

        fieldErrors.forEach(e-> errorsList.add(ApiError.builder()
                .fieldName(e.getField())
                .errorId(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .errorMessage(e.getDefaultMessage()).build()));

        log.error(errorsList.toString());

        return errorsList;
    }

    private void logException(Throwable throwable, HttpServletRequest httpServletRequest, String code){
        log.error("Error in session : {} with error code {}" , httpServletRequest.getRequestedSessionId(), code);
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        if (headerNames != null) {
            Map<String, String> map = Collections.list(headerNames).stream().collect(Collectors.toMap(e -> e, e -> httpServletRequest.getHeader(e)));
            log.error("Error with request headers: {} with error code {}", map, code);
        }
        log.error("Exception : {}", throwable);
    }
}
