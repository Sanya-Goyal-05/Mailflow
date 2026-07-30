package com.mailflow1.controller;

import com.mailflow1.entity.EmailTemplate;
import com.mailflow1.service.EmailTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class EmailTemplateController {

    private final EmailTemplateService service;

    public EmailTemplateController(EmailTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public EmailTemplate create(@RequestBody EmailTemplate template) {
        return service.createTemplate(template);
    }

    @GetMapping
    public List<EmailTemplate> getAll() {
        return service.getAllTemplates();
    }

    @GetMapping("/{id}")
    public EmailTemplate get(@PathVariable Long id) {
        return service.getTemplate(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteTemplate(id);
        return "Template Deleted";
    }
}