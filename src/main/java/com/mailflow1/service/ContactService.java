package com.mailflow1.service;

import com.mailflow1.entity.Campaign;
import com.mailflow1.entity.Contact;
import com.mailflow1.repository.CampaignRepository;
import com.mailflow1.repository.ContactRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final CampaignRepository campaignRepository;

    public ContactService(ContactRepository contactRepository,
                          CampaignRepository campaignRepository) {
        this.contactRepository = contactRepository;
        this.campaignRepository = campaignRepository;
    }
    public String uploadContacts(Long campaignId, MultipartFile file) {

        try {

            Campaign campaign = campaignRepository.findById(campaignId)
                    .orElseThrow(() -> new RuntimeException("Campaign not found"));

            Reader reader = new InputStreamReader(file.getInputStream());

            Iterable<CSVRecord> records =
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            List<Contact> contacts = new ArrayList<>();

            for (CSVRecord record : records) {

                Contact contact = new Contact();

                contact.setName(record.get("name"));
                contact.setEmail(record.get("email"));
                contact.setCampaign(campaign);

                contacts.add(contact);
            }

            // Save all contacts together
            contactRepository.saveAll(contacts);

            // Update campaign statistics
            campaign.setTotalEmails(contacts.size());
            campaign.setPendingEmails(contacts.size());

            campaignRepository.save(campaign);

            return "Contacts Uploaded Successfully";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}