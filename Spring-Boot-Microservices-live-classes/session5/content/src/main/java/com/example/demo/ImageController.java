package com.example.demo;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class ImageController {
//,produces = MediaType.APPLICATION_PDF_VALUE
    @GetMapping(value = "getFile",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getFile() throws IOException {
        String filePath = "D:\\Suresh\\Java-Training-Workspace\\madhi.pdf";
        File file = new File(filePath);
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity
                .ok()
                .header("Content-Disposition",
                        // Note: the trailing semicolon is important, don't remove.
                        String.format("attachment;filename=%s;", "test.pdf"))
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(inputStreamResource);
    }
}
