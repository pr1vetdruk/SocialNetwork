package ru.privetdruk.socialnetwork.util;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public abstract class FileUtils {
    public static String saveFile(String uploadPath, MultipartFile file) {
        String resultFileName = "";
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFileName = uuidFile + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            } catch (IOException ex) {
                resultFileName = "";
            }
        }
        return resultFileName;
    }
}
