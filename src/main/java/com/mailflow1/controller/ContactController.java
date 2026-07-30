package com.mailflow1.controller;

import com.mailflow1.service.ContactService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/campaigns")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(
            value = "/{campaignId}/contacts/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadContacts(
            @PathVariable Long campaignId,
            @RequestParam("file") MultipartFile file) {

        return contactService.uploadContacts(campaignId, file);
    }
}