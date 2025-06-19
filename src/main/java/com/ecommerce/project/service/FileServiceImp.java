package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile productImage) throws IOException {
        String originalFileName = productImage.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        // Replacing file name with random id preserving the file extension
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;

        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }

        Files.copy(productImage.getInputStream(), Paths.get(filePath));
        return fileName;
    }
}
