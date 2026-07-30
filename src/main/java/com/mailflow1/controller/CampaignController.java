package com.mailflow1.controller;

import com.mailflow1.entity.Campaign;
import com.mailflow1.service.CampaignService;
import org.springframework.web.bind.annotation.*;
import com.mailflow1.dto.CampaignAnalyticsResponse;
import com.mailflow1.dto.DashboardResponse;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public Campaign createCampaign(@PathVariable Long userId,
                                   @RequestBody Campaign campaign) {
        return campaignService.createCampaign(userId, campaign);
    }

    @GetMapping
    public List<Campaign> getCampaigns(@PathVariable Long userId) {
        return campaignService.getCampaignsByUser(userId);
    }
    @GetMapping("/{id}")
    public Campaign getCampaign(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }
    @GetMapping("/dashboard")
    public DashboardResponse getDashboard(@PathVariable Long userId) {
        return campaignService.getDashboard();
    }
    @GetMapping("/{campaignId}/analytics")
    public CampaignAnalyticsResponse getAnalytics(
            @PathVariable Long campaignId) {

        return campaignService.getAnalytics(campaignId);
    }

    @DeleteMapping("/{id}")
    public String deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return "Campaign deleted successfully!";
    }
}