package br.com.fiap.covidchurch.auth.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.fiap.covidchurch.auth.dtos.DefaultHttpJsonResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       return new ResponseEntity<>(new DefaultHttpJsonResponse<Object>(status.value(), ex.getMessage(), null), HttpStatus.BAD_REQUEST);
   }


//   @Override
//	protected ResponseEntity<Object> handleExceptionInternal(
//			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//       return new ResponseEntity<>(new DefaultHttpJsonResponse<Object>(status.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//   }

   
   
   @ExceptionHandler(value  = { NullPointerException.class })
    protected ResponseEntity<Object> handleNullPointer(
    		Exception ex, WebRequest request) {
        String bodyOfResponse = "Algo n�o foi tratado!!!";
        
        return new ResponseEntity<Object>(new DefaultHttpJsonResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), bodyOfResponse, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
   @ExceptionHandler(value  = { NotFoundException.class, NotFound.class })
   protected ResponseEntity<Object> handleNotFound(
   		Exception ex, WebRequest request) {
       
       return new ResponseEntity<Object>(new DefaultHttpJsonResponse<Object>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null), HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(value  = { DuplicatedException.class})
   protected ResponseEntity<Object> handleDuplicated(
   		Exception ex, WebRequest request) {
       return new ResponseEntity<Object>(new DefaultHttpJsonResponse<Object>(HttpStatus.CONFLICT.value(), ex.getMessage(), null), HttpStatus.CONFLICT);
   }
   
   @ExceptionHandler(value  = { Unauthorized.class, UnauthorizedException.class })
   protected ResponseEntity<Object> handleUnauthorized(
   		Exception ex, WebRequest request) {
       String bodyOfResponse = "N�o autorizado!!!";
       
       return new ResponseEntity<Object>(new DefaultHttpJsonResponse<Object>(HttpStatus.UNAUTHORIZED.value(), bodyOfResponse, null), HttpStatus.UNAUTHORIZED);
   }

   @ExceptionHandler(value  = { Exception.class})
   protected ResponseEntity<Object> handleGeneralException(
   		Exception ex, WebRequest request) {
       
       return new ResponseEntity<Object>(new DefaultHttpJsonResponse<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
}
