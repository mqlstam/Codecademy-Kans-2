package com.codecademy.domain;

public class ContactPerson {
    private String contactPersonEmail;
    private String nameContactPerson;

    public ContactPerson(String contactPersonEmail, String nameContactPerson) {
        this.contactPersonEmail = contactPersonEmail;
        this.nameContactPerson = nameContactPerson;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getNameContactPerson() {
        return nameContactPerson;
    }

    public void setNameContactPerson(String nameContactPerson) {
        this.nameContactPerson = nameContactPerson;
    }
}
