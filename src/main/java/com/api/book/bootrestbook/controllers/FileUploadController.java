package com.api.book.bootrestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.bootrestbook.helper.FileUploadHelper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @SuppressWarnings("null")
    @PostMapping("/upload-file")    
    public ResponseEntity<String> uplodFile(@RequestParam("file") MultipartFile file){
        // System.out.println(file.getOriginalFilename());
        // System.out.println(file.getSize());
        // System.out.println(file.getContentType());
        // System.out.println(file.getName());
        try {
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                        body("Request must contain file");
            }
            if(!file.getContentType().equals("image/png")){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Ony png is allowed");
            }

            boolean status = this.fileUploadHelper.uploadFile(file);
            if(status){
                return ResponseEntity.ok(
                    ServletUriComponentsBuilder.fromCurrentContextPath().
                    path("/image/").
                    path(file.getOriginalFilename()).
                        toUriString()
                    );
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try Again!");
    }
}
