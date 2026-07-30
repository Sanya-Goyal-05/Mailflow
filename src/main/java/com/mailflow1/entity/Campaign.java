package com.mailflow1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subject;
    private int totalEmails;

    private int sentEmails;

    private int failedEmails;

    private int pendingEmails;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String status;
    private LocalDateTime scheduledTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<>();
    public int getTotalEmails() {
        return totalEmails;
    }

    public void setTotalEmails(int totalEmails) {
        this.totalEmails = totalEmails;
    }

    public int getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(int sentEmails) {
        this.sentEmails = sentEmails;
    }

    public int getFailedEmails() {
        return failedEmails;
    }

    public void setFailedEmails(int failedEmails) {
        this.failedEmails = failedEmails;
    }

    public int getPendingEmails() {
        return pendingEmails;
    }

    public void setPendingEmails(int pendingEmails) {
        this.pendingEmails = pendingEmails;
    }

    public Campaign() {
    }

    public Campaign(Long id, String title, String subject, String content, String status, User user) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.content = content;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}