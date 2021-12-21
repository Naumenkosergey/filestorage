package com.example.filestorage.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class FileUtils {

    public static File convertMultipartToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
//            LOG.error("Error converting multipartFile to file",e)
        }
        return convertedFile;
    }

    public static String generateFileName(MultipartFile multipartFile) {
        return new Date().getTime() + "_" + Objects
                .requireNonNull(multipartFile.getOriginalFilename())
                .replaceAll(" ", "_");
    }
}
