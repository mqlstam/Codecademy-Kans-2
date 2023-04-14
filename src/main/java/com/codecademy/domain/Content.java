package com.codecademy.domain;


public class Content {
    protected int contentItemId;
    protected String contentTitle, contentDesc;
    protected String status;
    protected String publicationDate;
    

    public Content(int contentItemId, String contentDesc, String publicationDate, String status) {
        this.contentItemId = contentItemId;
        this.contentDesc = contentDesc;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public int getContentItemId() {
        return contentItemId;
    }

    public void setContentItemId(int contentItemId) {
        this.contentItemId = contentItemId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    
}
