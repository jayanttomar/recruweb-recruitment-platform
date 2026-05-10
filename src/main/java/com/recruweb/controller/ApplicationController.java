package com.recruweb.controller;

import com.recruweb.entity.Application;
import com.recruweb.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/application")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    // =========================
    // APPLY JOB API
    // =========================
    @PostMapping(value = "/apply", consumes = {"multipart/form-data"})
    public String applyJob(

            @RequestParam(value = "candidateName", required = false) String candidateName,
            @RequestParam(value = "candidateEmail", required = false) String candidateEmail,
            @RequestParam(value = "jobTitle", required = false) String jobTitle,
            @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "resume", required = false) MultipartFile file

    ) {

        try {

            if (
                isBlank(candidateName) ||
                isBlank(candidateEmail) ||
                isBlank(jobTitle) ||
                isBlank(company) ||
                file == null || file.isEmpty()
            ) {
                return "ERROR: Empty fields not allowed";
            }

            String uploadDir = System.getProperty("user.dir")
                    + File.separator + "uploads" + File.separator;

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = System.currentTimeMillis()
                    + "_" + file.getOriginalFilename();

            File saveFile = new File(uploadDir + fileName);

            Files.copy(
                    file.getInputStream(),
                    saveFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            Application application = new Application();

            application.setCandidateName(candidateName.trim());
            application.setCandidateEmail(candidateEmail.trim());
            application.setJobTitle(jobTitle.trim());
            application.setCompany(company.trim());
            application.setResume(fileName);
            application.setStatus("PENDING");

            applicationRepository.save(application);

            return "SUCCESS: Application Submitted";

        } catch (Exception e) {
            e.printStackTrace();
            return "FAILED: Server Error";
        }
    }

    // =========================
    // GET ALL APPLICATIONS
    // =========================
    @GetMapping
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    // =========================
    // UPDATE STATUS
    // =========================
    @PutMapping("/status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        Application app = applicationRepository.findById(id)
                .orElse(null);

        if (app == null) {
            return "Application not found";
        }

        app.setStatus(status);
        applicationRepository.save(app);

        return "Status updated to " + status;
    }

    // =========================
    // 🔥 RESUME DOWNLOAD API (NEW)
    // =========================
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadResume(@PathVariable String fileName) {

        try {

            String filePath = System.getProperty("user.dir")
                    + File.separator + "uploads"
                    + File.separator + fileName;

            File file = new File(filePath);

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(file.toURI());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // =========================
    // HELPER
    // =========================
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}