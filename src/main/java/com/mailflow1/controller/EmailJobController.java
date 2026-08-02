package com.mailflow1.controller;

import com.mailflow1.entity.EmailJob;
import com.mailflow1.service.EmailJobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class EmailJobController {

    private final EmailJobService service;

    public EmailJobController(EmailJobService service) {
        this.service = service;
    }

    @PostMapping("/generate/{campaignId}")
    public String generateJobs(@PathVariable Long campaignId) {
        return service.generateJobs(campaignId);
    }
    @PostMapping("/retry/{campaignId}")
    public String retryFailedJobs(@PathVariable Long campaignId) {
        return service.retryFailedJobs(campaignId);
    }
    @PostMapping("/retry/job/{jobId}")
    public String retryFailedJob(@PathVariable Long jobId) {
        return service.retryFailedJob(jobId);
    }
    @GetMapping("/failed")
    public List<EmailJob> getFailedJobs() {
        return service.getFailedJobs();
    }

    @GetMapping
    public List<EmailJob> getAllJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/pending")
    public List<EmailJob> getPendingJobs() {
        return service.getPendingJobs();
    }
}