package com.api.book.bootrestbook.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    
    public final String UPLOAD_DIR = "/Users/harshitasaxena/Documents/Sts/bootrestbook/src/main/resources/static/image";

    public boolean uploadFile(MultipartFile file){
        boolean status = false;
        try {
            // Create the destination path by converting the string to a Path object
            Path destinationPath = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());

            // Use Files.copy with the correct destination path
            Files.copy(file.getInputStream(), 
                destinationPath, 
                StandardCopyOption.REPLACE_EXISTING);

            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
