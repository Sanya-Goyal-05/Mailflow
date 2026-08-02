package com.mailflow1.repository;

import com.mailflow1.entity.EmailJob;
import com.mailflow1.enums.EmailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmailJobRepository extends JpaRepository<EmailJob, Long> {

    List<EmailJob> findByStatus(EmailStatus status);


    long countByStatus(EmailStatus status);
    Optional<EmailJob> findByIdAndStatus(Long id, EmailStatus status);

    @Query("""
    SELECT e FROM EmailJob e
    WHERE e.status = :status
    AND (
            e.scheduledTime IS NULL
            OR e.scheduledTime <= :time
        )
    ORDER BY
        CASE
            WHEN e.priority = 'HIGH' THEN 1
            WHEN e.priority = 'MEDIUM' THEN 2
            WHEN e.priority = 'LOW' THEN 3
        END
""")
    List<EmailJob> findPendingJobsByPriority(
            @Param("status") EmailStatus status,
            @Param("time") LocalDateTime time
    );
    List<EmailJob> findByCampaignIdAndStatus(Long campaignId, EmailStatus status);

}