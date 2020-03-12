package ru.privetdruk.socialnetwork.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public abstract class FileUtils {
    public static String extractFileNameFromUUIDString(String name) {
        return name.substring(name.indexOf('.', 0) + 1);
    }

    public static String generateFileName(String name) {
        return UUID.randomUUID().toString() + "." + name;
    }

    public static String saveFile(String uploadPath, MultipartFile file) {
        return saveFile(uploadPath, generateFileName(file.getOriginalFilename()), file);
    }

    public static String saveFile(String uploadPath, String fileName, MultipartFile file) {
        String resultFileName = null;
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            resultFileName = UUID.randomUUID().toString() + "." + fileName;

            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException ex) {
                resultFileName = "";
            }
        }
        return resultFileName;
    }
}
