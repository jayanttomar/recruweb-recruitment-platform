package com.recruweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")

public class Application {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;

    private String candidateName;

    private String candidateEmail;

    private String jobTitle;

    private String company;

    private String resume;

    private String status;

    public Application() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(
            String candidateName) {

        this.candidateName =
                candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(
            String candidateEmail) {

        this.candidateEmail =
                candidateEmail;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(
            String jobTitle) {

        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(
            String company) {

        this.company = company;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(
            String resume) {

        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }
}