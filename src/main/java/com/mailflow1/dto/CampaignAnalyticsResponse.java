package com.mailflow1.dto;

public class CampaignAnalyticsResponse {

    private Long campaignId;
    private String title;

    private int totalEmails;
    private int sentEmails;
    private int failedEmails;
    private int pendingEmails;

    public CampaignAnalyticsResponse() {
    }

    public CampaignAnalyticsResponse(Long campaignId,
                                     String title,
                                     int totalEmails,
                                     int sentEmails,
                                     int failedEmails,
                                     int pendingEmails) {
        this.campaignId = campaignId;
        this.title = title;
        this.totalEmails = totalEmails;
        this.sentEmails = sentEmails;
        this.failedEmails = failedEmails;
        this.pendingEmails = pendingEmails;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}