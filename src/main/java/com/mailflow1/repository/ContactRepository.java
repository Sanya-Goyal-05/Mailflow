package com.mailflow1.repository;

import com.mailflow1.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByCampaignId(Long campaignId);

}