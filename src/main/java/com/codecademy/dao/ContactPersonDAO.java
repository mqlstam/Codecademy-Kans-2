package com.codecademy.dao;

import com.codecademy.domain.ContactPerson;

import javafx.collections.ObservableList;

public interface ContactPersonDAO {
    ObservableList<ContactPerson> getContactPersons();

    
}
