package dev.sathu.movies.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {

    private final String FOLDER_PATH="/Users/sathursan/Documents/movies/src/main/java/dev/sathu/movies/service/";

    public String uploadImage(MultipartFile  file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        file.transferTo(new File(filePath));

        return "file uploaded successfully : " + filePath;

    }
}
