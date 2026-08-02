package com.mailflow1.service;

import com.mailflow1.entity.Campaign;
import com.mailflow1.entity.Contact;
import com.mailflow1.entity.EmailJob;
import com.mailflow1.enums.EmailStatus;
import com.mailflow1.repository.CampaignRepository;
import com.mailflow1.repository.ContactRepository;
import com.mailflow1.repository.EmailJobRepository;
import org.springframework.stereotype.Service;
import com.mailflow1.enums.Priority;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailJobService {

    private final CampaignRepository campaignRepository;
    private final ContactRepository contactRepository;
    private final EmailJobRepository emailJobRepository;

    public EmailJobService(CampaignRepository campaignRepository,
                           ContactRepository contactRepository,
                           EmailJobRepository emailJobRepository) {
        this.campaignRepository = campaignRepository;
        this.contactRepository = contactRepository;
        this.emailJobRepository = emailJobRepository;
    }

    public String generateJobs(Long campaignId) {

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        List<Contact> contacts = contactRepository.findByCampaignId(campaignId);

        for (Contact contact : contacts) {

            EmailJob job = new EmailJob();

            job.setRecipientEmail(contact.getEmail());
            job.setSubject(campaign.getSubject());
            job.setBody(campaign.getContent());
            job.setStatus(EmailStatus.PENDING);
            job.setPriority(Priority.MEDIUM);   // <-- Add it here
            job.setRetryCount(0);
            job.setCreatedAt(LocalDateTime.now());
            job.setScheduledTime(campaign.getScheduledTime());
            job.setCampaign(campaign);

            emailJobRepository.save(job);
        }

        return "Email Jobs Generated Successfully";
    }


    public List<EmailJob> getAllJobs() {
        return emailJobRepository.findAll();
    }

    public List<EmailJob> getPendingJobs() {
        return emailJobRepository.findByStatus(EmailStatus.PENDING);
    }
    public String retryFailedJobs(Long campaignId) {

        List<EmailJob> failedJobs = emailJobRepository
                .findByCampaignIdAndStatus(campaignId, EmailStatus.FAILED);

        if (failedJobs.isEmpty()) {
            return "No failed emails found.";
        }

        for (EmailJob job : failedJobs) {
            job.setStatus(EmailStatus.PENDING);
            job.setRetryCount(0);
            emailJobRepository.save(job);
        }

        return failedJobs.size() + " failed emails moved back to PENDING.";
    }
    public String retryFailedJob(Long jobId) {

        EmailJob job = emailJobRepository
                .findByIdAndStatus(jobId, EmailStatus.FAILED)
                .orElseThrow(() -> new RuntimeException("Failed email job not found"));

        job.setStatus(EmailStatus.PENDING);
        job.setRetryCount(0);

        emailJobRepository.save(job);

        return "Email job " + jobId + " moved back to PENDING.";
    }
    public List<EmailJob> getFailedJobs() {
        return emailJobRepository.findByStatus(EmailStatus.FAILED);
    }
}