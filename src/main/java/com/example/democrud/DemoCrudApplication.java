package com.example.democrud;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.democrud.service.api.FileUploadService;

@SpringBootApplication
public class DemoCrudApplication{
	@Resource
	 private FileUploadService fileService;
	public static void main(String[] args) {
		SpringApplication.run(DemoCrudApplication.class, args);
	}

}
