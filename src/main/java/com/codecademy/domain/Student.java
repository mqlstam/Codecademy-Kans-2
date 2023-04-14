package com.codecademy.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
    private String email, name, gender;
    private LocalDate birthDate;
    private Address adress;

    public Student(String email, String name, LocalDate birthDate, String gender, Address adress) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return adress;
    }

    public void setAddress(Address adress) {
        this.adress = adress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
