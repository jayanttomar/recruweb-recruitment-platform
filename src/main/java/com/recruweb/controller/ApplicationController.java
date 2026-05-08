package com.recruweb.controller;

import com.recruweb.entity.Application;
import com.recruweb.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/application")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping(
            value = "/apply",
            consumes = {"multipart/form-data"}
    )
    public String applyJob(

            @RequestParam("candidateName")
            String candidateName,

            @RequestParam("resume")
            MultipartFile file

    ) {

        try {

            String uploadDir =
                    System.getProperty("user.dir")
                            + File.separator
                            + "uploads"
                            + File.separator;

            System.out.println(uploadDir);

            File dir = new File(uploadDir);

            if (!dir.exists()) {

                dir.mkdirs();
            }

            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + file.getOriginalFilename();

            File saveFile =
                    new File(uploadDir + fileName);

            Files.copy(
                    file.getInputStream(),
                    saveFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            Application application =
                    new Application();

            application.setCandidateName(
                    candidateName
            );

            application.setResume(
                    fileName
            );

            applicationRepository.save(
                    application
            );

            return "Resume Uploaded Successfully";

        } catch (IOException e) {

            e.printStackTrace();

            return "Upload Failed";
        }
    }

    @GetMapping
    public List<Application> getApplications() {

        return applicationRepository.findAll();
    }
}