package com.mailflow1.service;

import com.mailflow1.entity.Campaign;
import com.mailflow1.entity.EmailJob;
import com.mailflow1.enums.EmailStatus;
import com.mailflow1.repository.CampaignRepository;
import com.mailflow1.repository.EmailJobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailWorker {

    private final EmailJobRepository repository;
    private final EmailSenderService emailSenderService;
    private final CampaignRepository campaignRepository;

    public EmailWorker(EmailJobRepository repository,
                       EmailSenderService emailSenderService,
                       CampaignRepository campaignRepository) {
        this.repository = repository;
        this.emailSenderService = emailSenderService;
        this.campaignRepository = campaignRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void processJobs() {

        System.out.println("Worker is running...");

        List<EmailJob> jobs = repository.findPendingJobsByPriority(
                EmailStatus.PENDING,
                LocalDateTime.now()
        );

        System.out.println("Pending jobs found: " + jobs.size());

        for (EmailJob job : jobs) {

            try {

                // Skip if campaign is scheduled for later
                if (job.getCampaign().getScheduledTime() != null &&
                        LocalDateTime.now().isBefore(job.getCampaign().getScheduledTime())) {

                    System.out.println("Campaign scheduled for later. Skipping email: "
                            + job.getRecipientEmail());

                    continue;
                }

                // Send email
                emailSenderService.sendEmail(
                        job.getRecipientEmail(),
                        job.getSubject(),
                        job.getBody()
                );

                // Update job status
                job.setStatus(EmailStatus.SENT);

                // Update campaign statistics
                Campaign campaign = job.getCampaign();

                campaign.setSentEmails(campaign.getSentEmails() + 1);
                campaign.setPendingEmails(campaign.getPendingEmails() - 1);

                campaignRepository.save(campaign);
                repository.save(job);

                System.out.println("Email sent to: " + job.getRecipientEmail());

            } catch (Exception e) {

                job.setRetryCount(job.getRetryCount() + 1);

                if (job.getRetryCount() >= 3) {

                    job.setStatus(EmailStatus.FAILED);

                    Campaign campaign = job.getCampaign();

                    campaign.setFailedEmails(campaign.getFailedEmails() + 1);
                    campaign.setPendingEmails(campaign.getPendingEmails() - 1);

                    campaignRepository.save(campaign);

                    System.out.println("Email permanently failed after 3 attempts: "
                            + job.getRecipientEmail());

                } else {

                    job.setStatus(EmailStatus.PENDING);

                    System.out.println("Retry "
                            + job.getRetryCount()
                            + " scheduled for: "
                            + job.getRecipientEmail());
                }

                repository.save(job);

                e.printStackTrace();
            }
        }
    }
}