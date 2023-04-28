package br.com.ferry.financeapi.config.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ HttpMessageNotReadableException.class,
			EmptyResultDataAccessException.class, PessoaInexistenteOuInativaException.class,
			MethodArgumentNotValidException.class })
	protected ResponseEntity<?> handleHttpMessageNotReadable() {
		return ResponseEntity.badRequest().build();
	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<?> handleDataIntegrityViolationException() {
		return ResponseEntity.notFound().build();
	}
}
