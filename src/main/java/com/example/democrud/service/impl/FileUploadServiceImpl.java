package com.example.democrud.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import com.example.democrud.service.api.FileUploadService;

public class FileUploadServiceImpl implements FileUploadService {
	private final String UPLOAD_DIR = "/filestorage/siase/";
	File file = new File(UPLOAD_DIR);
	@Override
	public void uploadFile(InputStream inputStream) {
		if (!file.exists()) {//no exist
            if (file.mkdirs()) {//create
                System.out.println("Directorio creado");
                try {
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    Path path = Paths.get(UPLOAD_DIR + "/" + UUID.randomUUID().toString());
                    Files.write(path, bytes);
                  } catch (IOException e) {
                	  System.out.println(e.getMessage());
                  }
            } else {
                System.out.println("Error al crear directorio");
            }
        }
		
	}
}
