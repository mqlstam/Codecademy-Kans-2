package com.codecademy.dao;

import com.codecademy.domain.Certificate;
import javafx.collections.ObservableList;


public interface CertificateDAO {
    ObservableList<Certificate> getCertificates();
    void addCertificate(Certificate certificate);
    void updateCertificate(Certificate certificate);
    void deleteCertificate(Certificate certificate);
    // public void issueCertificateIfCompleted(String emailAddress, int courseId);
}
