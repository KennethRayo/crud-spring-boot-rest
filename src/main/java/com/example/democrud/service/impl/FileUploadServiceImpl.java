package com.example.democrud.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.democrud.service.api.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	private final String UPLOAD_DIR = "/filestorage/siase";
	private final Path root = Paths.get(UPLOAD_DIR);
	File file = new File(UPLOAD_DIR);
	
	@Override
	public void init() {
		try {
			if (file.exists()) {//exist
				System.out.println("Directory exist");
				Files.createDirectory(root);
			}
			else {
				 if (file.mkdirs()) {//create
					 System.out.println("Directorio creado");
					 Files.createDirectory(root);
				 }
				 else {
					 System.out.println("Error al crear directorio");
				 }
			}
			
		} catch (IOException e) {
			throw new RuntimeException("No se puede inicializar la carpeta de archivos");
		}
		
	}
	@Override
	public void save(MultipartFile file) {
		try {
            //copy (que queremos copiar, a donde queremos copiar)
			/*byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            Path path = Paths.get(UPLOAD_DIR + "/" + UUID.randomUUID().toString());
            Files.write(path, bytes);*/
            String name = file.getContentType();
           
 
			Files.copy(file.getInputStream(), 
                       this.root.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
        }
		
	}
	@Override
	public Resource load(String filename) {
		try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
	}
	@Override
	public void deleteAll() {
		 FileSystemUtils.deleteRecursively(root.toFile());
		
	}
	@Override
	public Stream<Path> loadAll() {
		//Files.walk recorre nuestras carpetas (uploads) buscando los archivos
        // el 1 es la profundidad o nivel que queremos recorrer
        // :: Referencias a metodos
        // Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
        try{
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
	}
	@Override
	public String deleteFile(String filename) {
		 try {
	            Boolean delete = Files.deleteIfExists(this.root.resolve(filename));
	                return "Borrado";
	        }catch (IOException e){
	            e.printStackTrace();
	            return "Error Borrando ";
	        }
	}
}
