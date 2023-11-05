package dz.web.api.algeriacitiesdetails.exception;


import dz.web.api.algeriacitiesdetails.helper.Utils;
import dz.web.api.algeriacitiesdetails.model.FieldErrorModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice  {



    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> methodNototSupported(HttpServletRequest request, HttpRequestMethodNotSupportedException exception){

        log.error("Method Not Supported  {} for {} Host {}", request.getMethod(),request.getRequestURI(), Utils.getIpAddressFromHeader(request));
        return new ResponseEntity<>("Method Not Supported ", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<FieldErrorModel>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, List<String>> fieldsErrorsMap = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(fieldError -> {
                    List<String> values = new ArrayList<>();
                    if (fieldsErrorsMap.containsKey(fieldError.getField())){
                        values = fieldsErrorsMap.get(fieldError.getField());
                    }
                    values.add(fieldError.getDefaultMessage());
                    //values.add(messageSource.getMessage(fieldError.getDefaultMessage(),null, new Locale("fr")));
                    fieldsErrorsMap.put(fieldError.getField(), values);
                });

        List<FieldErrorModel> errors = new ArrayList<>();
        fieldsErrorsMap.entrySet().stream().forEach(entry -> {
            errors.add(new FieldErrorModel(entry.getKey(), entry.getValue()));
        });

        log.error("Invalid Argument {} ", errors.toString());
        return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);

    }



    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> methodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {

        log.error("Arguments Not Valid for {} {}", request.getRequestURI(), Utils.getIpAddressFromHeader(request));
        return new ResponseEntity<>("Argument not Valid", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<String> notFoundException(HttpServletRequest request, NoHandlerFoundException exception) {

        log.error("URL Not Valid for {}  {}", request.getRequestURI(), Utils.getIpAddressFromHeader(request));

        return new ResponseEntity<>("URL Not Found", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(HttpServletRequest request, Exception exception) {
        log.error("Global Exception {} {} {}",exception.getClass() ,exception.getMessage(),Utils.getIpAddressFromHeader(request));

        exception.printStackTrace();
        return new ResponseEntity<>("Internal Exception", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
