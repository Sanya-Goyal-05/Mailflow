package com.mailflow1.service;

import com.mailflow1.dto.CampaignAnalyticsResponse;
import com.mailflow1.dto.DashboardResponse;
import com.mailflow1.entity.Campaign;
import com.mailflow1.entity.User;
import com.mailflow1.repository.CampaignRepository;
import com.mailflow1.repository.ContactRepository;
import com.mailflow1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    public CampaignService(CampaignRepository campaignRepository,
                           UserRepository userRepository,
                           ContactRepository contactRepository) {
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    public Campaign createCampaign(Long userId, Campaign campaign) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        campaign.setUser(user);

        return campaignRepository.save(campaign);
    }

    public List<Campaign> getCampaignsByUser(Long userId) {
        return campaignRepository.findByUserId(userId);
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public DashboardResponse getDashboard() {

        List<Campaign> campaigns = campaignRepository.findAll();

        int sent = 0;
        int failed = 0;
        int pending = 0;

        for (Campaign campaign : campaigns) {
            sent += campaign.getSentEmails();
            failed += campaign.getFailedEmails();
            pending += campaign.getPendingEmails();
        }

        return new DashboardResponse(
                campaigns.size(),
                (int) contactRepository.count(),
                sent,
                failed,
                pending
        );
    }

    public CampaignAnalyticsResponse getAnalytics(Long campaignId) {

        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        return new CampaignAnalyticsResponse(
                campaign.getId(),
                campaign.getTitle(),
                campaign.getTotalEmails(),
                campaign.getSentEmails(),
                campaign.getFailedEmails(),
                campaign.getPendingEmails()
        );
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}