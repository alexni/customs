package ru.customs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.customs.entity.DeclarantEntity;
import ru.customs.entity.DeclarantState;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeclarantResponse {
    @JsonProperty
    Long id;
    @JsonProperty
    String surname;
    @JsonProperty
    String name;
    @JsonProperty
    String patronymic;
    @JsonProperty
    String birthday;
    @JsonProperty("passport_series")
    String passportSeries;
    @JsonProperty("passport_number")
    String passportNumber;
    @JsonProperty("passport_date")
    String passportDate;
    @JsonProperty
    String phone;
    @JsonProperty
    DeclarantState state;

    public DeclarantResponse() {
    }

    public DeclarantResponse(DeclarantEntity declarant) {
        this.id = declarant.getId();
        this.surname = declarant.getSurname();
        this.name = declarant.getName();
        this.patronymic = declarant.getPatronymic();
        this.birthday = declarant.getBirthday();
        this.passportDate = declarant.getPassportDate();
        this.passportSeries = declarant.getPassportSeries();
        this.passportNumber = declarant.getPassportNumber();
        this.phone = declarant.getPhone();
        this.state = declarant.getState();
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
}
