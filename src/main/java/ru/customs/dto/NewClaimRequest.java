package ru.customs.dto;

import ru.customs.entity.ClaimState;
import ru.customs.entity.OperationType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewClaimRequest {

    private String surname;
    private String name;
    private String secondName;
    private String phone;
    private String birthdate;
    private String passportPrefix;
    private String passportNumber;
    private String   passportDate;
    private OperationType operationType;
    private Set<OperationType> operationTypes;
    private String contractNumber;
    private String trackNumber;
    private String trailerNumber;
    private String checkPoint;
    private String payer;
    private String carrier;
    private String comment;

    private Set<String> documents = new HashSet<>();

    private ClaimState state;
    private Long timestamp;
    private Long managerId;
    private boolean haveNewMessages;

    public NewClaimRequest(String surname, String name, String secondName, String phone, String birthdate, String passportPrefix, String passportNumber, String passportDate, OperationType operationType, String trackNumber, String trailerNumber, String checkPoint, String payer, String carrier, String comment, Set<String> documents, Set<OperationType> operationTypes, String contractNumber,ClaimState state, Long timestamp, Long managerId, boolean haveNewMessages) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.phone = phone;
        this.birthdate = birthdate;
        this.passportPrefix = passportPrefix;
        this.passportNumber = passportNumber;
        this.passportDate = passportDate;
        this.operationType = operationType;
        this.trackNumber = trackNumber;
        this.trailerNumber = trailerNumber;
        this.checkPoint = checkPoint;
        this.payer = payer;
        this.carrier = carrier;
        this.comment = comment;
        this.documents = documents;
        this.operationTypes = operationTypes;
        this.contractNumber = contractNumber;
        this.state = state;
        this.timestamp = timestamp;
        this.managerId = managerId;
        this.haveNewMessages = haveNewMessages;
    }

    @Override
    public String toString() {
        return "NewClaimRequest{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", passportPrefix='" + passportPrefix + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportDate='" + passportDate + '\'' +
                ", operationType=" + operationType +
                ", trackNumber='" + trackNumber + '\'' +
                ", trailerNumber='" + trailerNumber + '\'' +
                ", checkPoint='" + checkPoint + '\'' +
                ", payer='" + payer + '\'' +
                ", carrier='" + carrier + '\'' +
                ", comment='" + comment + '\'' +
                ", documents=" + documents +
                ", state=" + state +
                ", timestamp=" + timestamp +
                ", managerId=" + managerId +
                ", haveNewMessages=" + haveNewMessages +
                '}';
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassportPrefix() {
        return passportPrefix;
    }

    public void setPassportPrefix(String passportPrefix) {
        this.passportPrefix = passportPrefix;
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

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public String getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(String checkPoint) {
        this.checkPoint = checkPoint;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<String> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<String> documents) {
        this.documents = documents;
    }

    public ClaimState getState() {
        return state;
    }

    public void setState(ClaimState state) {
        this.state = state;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public boolean isHaveNewMessages() {
        return haveNewMessages;
    }

    public void setHaveNewMessages(boolean haveNewMessages) {
        this.haveNewMessages = haveNewMessages;
    }

    public Set<OperationType> getOperationTypes() {
        return operationTypes;
    }

    public void setOperationTypes(Set<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
