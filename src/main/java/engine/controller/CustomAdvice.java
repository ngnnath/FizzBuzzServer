package engine.controller;

import engine.errors.WrongParamRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * CustomAdvice.
 * custom the 404 page.
 */
@RestControllerAdvice
public class CustomAdvice {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Object> handlerWrongPathException(NoHandlerFoundException e) {
		return new ResponseEntity<>("Wrong request, please check the available requests in http://localhost:8081/swagger-ui.html", new HttpHeaders(),
			HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(WrongParamRequestException.class)
	public ResponseEntity<Object> handlerParamsException(WrongParamRequestException e) {
		return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}

