package com.codecademy.dao;

import com.codecademy.domain.Content;

import javafx.collections.ObservableList;

public interface ContentDAO {
    ObservableList<Content> getContents();
    void addContent(Content content);
    void updateContent(Content content);
    void deleteContent(Content content);
}
