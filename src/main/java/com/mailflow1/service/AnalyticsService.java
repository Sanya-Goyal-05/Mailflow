package com.mailflow1.service;

import com.mailflow1.dto.AnalyticsResponse;
import com.mailflow1.entity.EmailJob;
import com.mailflow1.enums.EmailStatus;
import com.mailflow1.repository.EmailJobRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    private final EmailJobRepository emailJobRepository;

    public AnalyticsService(EmailJobRepository emailJobRepository) {
        this.emailJobRepository = emailJobRepository;
    }

    public AnalyticsResponse getAnalytics() {

        long total = emailJobRepository.count();

        long sent = emailJobRepository.countByStatus(EmailStatus.SENT);

        long failed = emailJobRepository.countByStatus(EmailStatus.FAILED);

        long pending = emailJobRepository.countByStatus(EmailStatus.PENDING);

        double successRate = 0;

        if (total > 0) {
            successRate = ((double) sent / total) * 100;
        }

        return new AnalyticsResponse(
                total,
                sent,
                failed,
                pending,
                successRate
        );

    }
    public void markOpened(Long jobId) {

        EmailJob job = emailJobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Email Job not found"));

        job.setStatus(EmailStatus.OPENED);

        emailJobRepository.save(job);
    }
}