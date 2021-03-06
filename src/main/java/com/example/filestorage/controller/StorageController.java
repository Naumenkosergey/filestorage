package com.example.filestorage.controller;

import com.example.filestorage.response.Response;
import com.example.filestorage.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class StorageController {
    @Autowired
    private StorageService service;

    @PostMapping("/upload")
    public Response uploadFile(@RequestParam(value = "file") MultipartFile file) {
        String s = service.uploadFile(file);
        return Response.success().status(200).data(s).build();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);

    }


    @DeleteMapping("delete/{fileName}")
    public ResponseEntity<String> DeleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }

    @GetMapping("/test")
    public String GetTest() {
        return "I'm test!";
    }

}
