package com.mailflow1.dto;

public class DashboardResponse {

    private long totalCampaigns;
    private long totalContacts;
    private int totalEmailsSent;
    private int totalEmailsFailed;
    private int totalEmailsPending;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalCampaigns,
                             long totalContacts,
                             int totalEmailsSent,
                             int totalEmailsFailed,
                             int totalEmailsPending) {
        this.totalCampaigns = totalCampaigns;
        this.totalContacts = totalContacts;
        this.totalEmailsSent = totalEmailsSent;
        this.totalEmailsFailed = totalEmailsFailed;
        this.totalEmailsPending = totalEmailsPending;
    }

    public long getTotalCampaigns() {
        return totalCampaigns;
    }

    public void setTotalCampaigns(long totalCampaigns) {
        this.totalCampaigns = totalCampaigns;
    }

    public long getTotalContacts() {
        return totalContacts;
    }

    public void setTotalContacts(long totalContacts) {
        this.totalContacts = totalContacts;
    }

    public int getTotalEmailsSent() {
        return totalEmailsSent;
    }

    public void setTotalEmailsSent(int totalEmailsSent) {
        this.totalEmailsSent = totalEmailsSent;
    }

    public int getTotalEmailsFailed() {
        return totalEmailsFailed;
    }

    public void setTotalEmailsFailed(int totalEmailsFailed) {
        this.totalEmailsFailed = totalEmailsFailed;
    }

    public int getTotalEmailsPending() {
        return totalEmailsPending;
    }

    public void setTotalEmailsPending(int totalEmailsPending) {
        this.totalEmailsPending = totalEmailsPending;
    }
}