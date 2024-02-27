package dz.web.api.algeriacitiesdetails.exception;


import dz.web.api.algeriacitiesdetails.helper.Utils;
import dz.web.api.algeriacitiesdetails.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        Map<String,String> errors = new HashMap<>();
        List<ObjectError> list = ex.getBindingResult().getAllErrors();
        list.forEach(objectError -> errors.put(objectError.getObjectName(), objectError.getDefaultMessage()));

        log.error("Invalid Argument {} for {} Host {}", servletWebRequest.getHttpMethod().name(),servletWebRequest.getRequest().getRequestURI(), Utils.getIpAddressFromHeader(servletWebRequest.getRequest()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("Content Type Not Supported {} for {} Host {}", servletWebRequest.getHttpMethod().name(),servletWebRequest.getRequest().getRequestURI(), Utils.getIpAddressFromHeader(servletWebRequest.getRequest()));
        return new ResponseEntity<>("Content Type Not Supported ", HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("Invalid Conversion {} for {} Host {}", servletWebRequest.getHttpMethod().name(),servletWebRequest.getRequest().getRequestURI(), Utils.getIpAddressFromHeader(servletWebRequest.getRequest()));
        return new ResponseEntity<>("Invalid Conversion ", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        log.error("Can't convert {} with value {} to {} -- Path {} Host " ,ex.getPropertyName(),ex.getValue(),ex.getRequiredType().getSimpleName(),
                                                                            servletWebRequest.getHttpMethod().name(),servletWebRequest.getRequest().getRequestURI(),
                                                                            Utils.getIpAddressFromHeader(servletWebRequest.getRequest()));
        return new ResponseEntity<>("Value "+ ex.getValue()+" not Valid for Param "+ex.getPropertyName(), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("Method Not Supported  {} for {} Host {}", servletWebRequest.getRequest().getMethod(),servletWebRequest.getRequest().getRequestURI(), Utils.getIpAddressFromHeader(servletWebRequest.getRequest()));
        return new ResponseEntity<>("Method Not Supported ", HttpStatus.METHOD_NOT_ALLOWED);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("URL Not Valid for {}  {}", servletWebRequest.getRequest().getRequestURI(), Utils.getIpAddressFromHeader(servletWebRequest.getRequest()));

        return new ResponseEntity<>("URL Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>("Path Variable Note Found", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("Required Param  {} for {}  {}",ex.getParameterName(), servletWebRequest.getRequest().getRequestURI(), Utils.getIpAddressFromHeader(servletWebRequest.getRequest()));
        return new ResponseEntity<>("Required Param "+ ex.getParameterName(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>("Extra Param "+ ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<Object> constraintViolation( ConstraintViolationException ex, HttpServletRequest request){

        List<String> errors = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> exception(HttpServletRequest request, GenericException exception){
        log.error("Error at {} with message {} " , LocalDateTime.now(), exception.getMessage());

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(HttpServletRequest request, Exception exception) {
        log.error("Global Exception {} {} {}",exception.getClass() ,exception.getMessage(),Utils.getIpAddressFromHeader(request));

       // exception.printStackTrace();
        return new ResponseEntity<>("Internal Exception", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
