package com.recruweb.controller;

import com.recruweb.repository.JobRepository;
import com.recruweb.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/stats")
    public Map<String, Long> getStats() {

        Map<String, Long> map = new HashMap<>();

        map.put("totalJobs", jobRepository.count());
        map.put("totalApplications", applicationRepository.count());

        return map;
    }
}