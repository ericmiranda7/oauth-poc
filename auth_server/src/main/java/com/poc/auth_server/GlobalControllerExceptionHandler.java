package com.poc.auth_server;

import com.poc.auth_server.exception.AuthorizationCodeNotValidException;
import com.poc.auth_server.exception.ConsentNotGrantedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
  
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({
          ConsentNotGrantedException.class,
          AuthorizationCodeNotValidException.class
  } )
  public void handleForbidden() {
  }
}
