package ru.privetdruk.socialnetwork.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public abstract class FileUtils {
    public static String extractFileNameFromUUIDString(String name) {
        return name == null ? null : name.substring(name.indexOf('.', 0) + 1);
    }

    public static String generateFileName(String name) {
        return UUID.randomUUID().toString() + "." + name;
    }

    public static String saveFile(String uploadPath, MultipartFile file) {
        String fileName = null;
        if (!file.isEmpty()) {
            fileName = generateFileName(file.getOriginalFilename());
            saveFile(uploadPath, fileName, file);
        }
        return fileName;
    }

    public static void saveFile(String uploadPath, String fileName, MultipartFile file) {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                file.transferTo(new File(uploadPath + "/" + fileName));
            } catch (IOException e) {
                fileName = "error";
            }
        }
    }
}
