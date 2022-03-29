package com.hoang.travel.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Component
public class UploadFile {
    @Value("${app.upload.dir}")
    private String UPLOAD_DIR;

    public String updateImage(MultipartFile file) {
        Path path = Paths.get(UPLOAD_DIR);
       // String nameImg = file.getOriginalFilename();
        int min = 100;
        int max = 999;
        int random_int;
        random_int = (int) (Math.random() * (max - min + 1) + min);
        Date data = new Date();
        String dates = DateUtil.getDate(data);
        String nameImg=dates+String.valueOf(random_int);
        String lastName= null;
        //String a= file.getOriginalFilename();
       // String [] arr =a.split(".",1);
        for(String name : file.getContentType().split("/")){
            lastName = name;
        }
        nameImg = nameImg+"."+lastName;
        try {
            Files.copy(file.getInputStream()
                    , path.resolve(nameImg)
                    , StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            System.out.println(e);
        }
        return nameImg;
    }
}
