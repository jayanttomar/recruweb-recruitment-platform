package com.recruweb.service;

import com.recruweb.entity.Application;
import com.recruweb.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository
            applicationRepository;

    // Save Application
    public Application saveApplication(
            Application application) {

        return applicationRepository
                .save(application);
    }

    // Get All Applications
    public List<Application>
    getApplications() {

        return applicationRepository
                .findAll();
    }
}