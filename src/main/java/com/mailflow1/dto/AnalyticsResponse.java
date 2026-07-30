package com.mailflow1.dto;

public class AnalyticsResponse {

    private long totalEmails;
    private long sentEmails;
    private long failedEmails;
    private long pendingEmails;
    private double successRate;

    public AnalyticsResponse() {}

    public AnalyticsResponse(long totalEmails,
                             long sentEmails,
                             long failedEmails,
                             long pendingEmails,
                             double successRate) {
        this.totalEmails = totalEmails;
        this.sentEmails = sentEmails;
        this.failedEmails = failedEmails;
        this.pendingEmails = pendingEmails;
        this.successRate = successRate;
    }

    public long getTotalEmails() {
        return totalEmails;
    }

    public void setTotalEmails(long totalEmails) {
        this.totalEmails = totalEmails;
    }

    public long getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(long sentEmails) {
        this.sentEmails = sentEmails;
    }

    public long getFailedEmails() {
        return failedEmails;
    }

    public void setFailedEmails(long failedEmails) {
        this.failedEmails = failedEmails;
    }

    public long getPendingEmails() {
        return pendingEmails;
    }

    public void setPendingEmails(long pendingEmails) {
        this.pendingEmails = pendingEmails;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }
}