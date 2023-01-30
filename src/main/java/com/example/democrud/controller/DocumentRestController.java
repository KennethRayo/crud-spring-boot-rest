package com.example.democrud.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.democrud.model.Document;
import com.example.democrud.service.api.FileUploadService;

@RestController
@RequestMapping(value = "/api/v1/file/upload/")
@CrossOrigin("*")
public class DocumentRestController {

	@Autowired
	 private FileUploadService fileUploadService;

	@GetMapping(value = "/upload")
	public ResponseEntity<Document> handleFileUpload(@Valid @RequestBody Document doc) {
		Document object = new Document();
		if(doc.getFile().getSize() > 0) {
			try {
				fileUploadService.uploadFile(doc.getFile().getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			return new ResponseEntity<Document>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Document>(object, HttpStatus.OK);
	}
}
