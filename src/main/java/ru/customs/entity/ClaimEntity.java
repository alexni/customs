package ru.customs.entity;

import ru.customs.dto.NewClaimRequest;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "claims")
public class ClaimEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surname;
    private String name;
    private String secondName;
    private String phone;
    private String   birthdate;
    private String passportPrefix;
    private String passportNumber;
    private String   passportDate;
    private OperationType operationType;
    private String trackNumber;
    private String trailerNumber;
    private String checkPoint;
    private String payer;
    private String contractNumber;
    private String carrier;
    private String comment;

    @ElementCollection
    @CollectionTable(name = "documenta", joinColumns = @JoinColumn(name = "claim_id"))
    @Column(name = "document")
    private Set<String> documents = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "operation_types", joinColumns = @JoinColumn(name = "claim_id"))
    @Column(name = "operation_type")
    private Set<OperationType> operationTypes = new HashSet<>();

    private ClaimState state;
    private Long timestamp;
    private Long managerId;
    private boolean haveNewMessages;

    public ClaimEntity() {
    }

    public ClaimEntity(Long id, String surname, String name, String secondName, String phone, String birthdate, String passportPrefix, String passportNumber, String passportDate, OperationType operationType, String trackNumber, String trailerNumber, String checkPoint, String payer, String carrier, String comment, Set<String> documents, Set<OperationType> operationTypes, String contractNumber, ClaimState state, Long timestamp, Long managerId, boolean haveNewMessages) {
        this.id = id;
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
        this.state = state;
        this.timestamp = timestamp;
        this.managerId = managerId;
        this.haveNewMessages = haveNewMessages;
        this.operationTypes = operationTypes;
        this.contractNumber = contractNumber;
    }

    public ClaimEntity(NewClaimRequest other){
        this.surname = other.getSurname();
        this.name = other.getName();
        this.secondName = other.getSecondName();
        this.phone = other.getPhone();
        this.birthdate = other.getBirthdate();
        this.passportDate = other.getPassportDate();
        this.passportPrefix = other.getPassportPrefix();
        this.passportNumber = other.getPassportNumber();
        this.operationType = other.getOperationType();
        this.trackNumber = other.getTrackNumber();
        this.trailerNumber = other.getTrailerNumber();
        this.checkPoint = other.getCheckPoint();
        this.payer = other.getPayer();
        this.carrier = other.getCarrier();
        this.comment = other.getComment();
        this.documents = other.getDocuments();
        this.state = ClaimState.Start;
        this.timestamp = new Date().getTime();
        this.managerId = 0L;
        this.haveNewMessages = false;
        this.operationTypes= other.getOperationTypes();
        this.contractNumber = other.getContractNumber();
    }

    public ClaimEntity(String surname, String name, String secondName, String phone, String birthdate, String passportPrefix, String passportNumber, String passportDate, OperationType operationType, String trackNumber, String trailerNumber, String checkPoint, String payer, String carrier, String comment, Set<String> documents, ClaimState state, Long timestamp, Long managerId, boolean haveNewMessages, Set<OperationType> operationTypes, String contractNumber) {
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
        this.state = state;
        this.timestamp = timestamp;
        this.managerId = managerId;
        this.haveNewMessages = haveNewMessages;
        this.operationTypes = operationTypes;
        this.contractNumber = contractNumber;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPassportPrefix() {
        return passportPrefix;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPassportDate() {
        return passportDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public String getCheckPoint() {
        return checkPoint;
    }

    public String getPayer() {
        return payer;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getComment() {
        return comment;
    }

    public Set<String> getDocuments() {
        return documents;
    }

    public ClaimState getState() {
        return state;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Long getManagerId() {
        return managerId;
    }

    public boolean isHaveNewMessages() {
        return haveNewMessages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setPassportPrefix(String passportPrefix) {
        this.passportPrefix = passportPrefix;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setPassportDate(String passportDate) {
        this.passportDate = passportDate;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public void setCheckPoint(String checkPoint) {
        this.checkPoint = checkPoint;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDocuments(Set<String> documents) {
        this.documents = documents;
    }

    public void setState(ClaimState state) {
        this.state = state;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public void setHaveNewMessages(boolean haveNewMessages) {
        this.haveNewMessages = haveNewMessages;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Set<OperationType> getOperationTypes() {
        return operationTypes;
    }

    public void setOperationTypes(Set<OperationType> operationTypes) {
        this.operationTypes = operationTypes;
    }
}
