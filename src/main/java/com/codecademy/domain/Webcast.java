package com.codecademy.domain;

public class Webcast {
    private String titel, description, hostName, organisationName, speakername, url;

    public Webcast(String titel, String description, String hostName, String organisationName, String speakername, String url) {
        this.titel = titel;
        this.description = description;
        this.hostName = hostName;
        this.organisationName = organisationName;
        this.url = url;
        this.speakername = speakername;
    }
}
