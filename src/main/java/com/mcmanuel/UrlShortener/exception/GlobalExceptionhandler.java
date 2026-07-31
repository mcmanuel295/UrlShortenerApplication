package com.mcmanuel.UrlShortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(ShortCodeNotFoundException.class)
    public ProblemDetail shortCodeNotFoundException(ShortCodeNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Short Code Not Found Exception");
        problemDetail.setProperty("Timestamp", LocalDateTime.now());
        return problemDetail;

    }

    @ExceptionHandler(OriginalUrlNotFoundException.class)
    public ProblemDetail originalUrlNotFoundException(OriginalUrlNotFoundException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
        problemDetail.setTitle("Original Url Not Found Exception");
        problemDetail.setProperty("Timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(CodeGenerationException.class)
    public ProblemDetail codeGenerationException(CodeGenerationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Unique Code Generation Failure");
        problemDetail.setProperty("Timestamp", LocalDateTime.now());
        return problemDetail;

    }
}
