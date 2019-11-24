package ru.customs.entity;

import ru.customs.dto.NewClaimRequest;
import ru.customs.service.DeclarantService;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DeclarantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String surname;
    String name;
    String patronymic;
    String birthday;
    String passportSeries;
    String passportNumber;
    String passportDate;
    String phone;
    DeclarantState state;
    @Column(unique = true)
    String firebase;


    public DeclarantEntity() {
    }

    public DeclarantEntity(String surname, String name, String patronymic, String birthday, String passportSeries, String passportNumber, String passportDate, String phone, DeclarantState state, String token) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.passportDate = passportDate;
        this.phone = phone;
        this.state = state;
        this.firebase = token;
    }

    public DeclarantEntity(NewClaimRequest req, String token) {
        this.surname = req.getSurname();
        this.name = req.getName();
        this.patronymic = req.getSecondName();
        this.birthday = req.getBirthdate().toString();
        this.passportSeries = req.getPassportPrefix();
        this.passportNumber = req.getPassportNumber();
        this.passportDate = req.getPassportDate().toString();
        this.phone = req.getPhone();
        this.state = DeclarantState.ACTIVE;
        this.firebase = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(String passportDate) {
        this.passportDate = passportDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DeclarantState getState() {
        return state;
    }

    public void setState(DeclarantState state) {
        this.state = state;
    }

    public String getFirebase() {
        return firebase;
    }

    public void setFirebase(String firebase) {
        this.firebase = firebase;
    }
}
