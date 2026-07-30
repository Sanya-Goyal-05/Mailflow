package com.mailflow1.service;

import com.mailflow1.entity.EmailTemplate;
import com.mailflow1.repository.EmailTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailTemplateService {

    private final EmailTemplateRepository repository;

    public EmailTemplateService(EmailTemplateRepository repository) {
        this.repository = repository;
    }

    public EmailTemplate createTemplate(EmailTemplate template) {
        return repository.save(template);
    }

    public List<EmailTemplate> getAllTemplates() {
        return repository.findAll();
    }

    public EmailTemplate getTemplate(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    public void deleteTemplate(Long id) {
        repository.deleteById(id);
    }
}