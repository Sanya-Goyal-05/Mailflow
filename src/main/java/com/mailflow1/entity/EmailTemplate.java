package com.mailflow1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "email_templates")
public class EmailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    public EmailTemplate() {
    }

    public EmailTemplate(Long id, String name, String subject, String body) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}