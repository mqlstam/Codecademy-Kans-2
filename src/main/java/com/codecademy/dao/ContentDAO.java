package com.codecademy.dao;

import com.codecademy.domain.Content;

import javafx.collections.ObservableList;

public interface ContentDAO {
    ObservableList<Content> getContents();
}
