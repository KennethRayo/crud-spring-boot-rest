package com.example.democrud.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.example.democrud.model.FileMessage;

@ControllerAdvice
public class FileUploadException {
	 @ExceptionHandler(MaxUploadSizeExceededException.class)
	    public ResponseEntity<FileMessage> maxSizeException(MaxUploadSizeExceededException exc){
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
	                .body(new FileMessage("Uno o mas archivos exceden el tamaño maximo"));
	    }
}
