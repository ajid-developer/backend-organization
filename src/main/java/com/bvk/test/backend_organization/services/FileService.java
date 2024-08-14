package com.bvk.test.backend_organization.services;

import com.bvk.test.backend_organization.components.FileStrogeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private Path fileLocation;

    @Autowired
    public FileService(FileStrogeProperties fileStrogeProperties) throws IOException {
        this.fileLocation = Paths.get(fileStrogeProperties.getUploadDir()).toAbsolutePath().normalize();
        Files.createDirectories(this.fileLocation);
    }

    public String saveImage(MultipartFile file) throws IOException {
        String fileName = String.valueOf(System.currentTimeMillis()) + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        Path targetLocation = this.fileLocation.resolve(fileName);
        System.out.println("Target " + targetLocation);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return fileName;

    }
}
