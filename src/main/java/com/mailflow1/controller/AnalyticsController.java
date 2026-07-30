package com.mailflow1.controller;
import com.mailflow1.dto.AnalyticsResponse;
import com.mailflow1.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PutMapping("/open/{jobId}")
    public String markOpened(@PathVariable Long jobId) {
        analyticsService.markOpened(jobId);
        return "Email marked as opened";
    }

    @GetMapping
    public AnalyticsResponse getAnalytics() {
        return analyticsService.getAnalytics();
    }
}