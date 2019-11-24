package ru.customs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.customs.entity.ClaimEntity;
import ru.customs.entity.ClaimState;
import ru.customs.entity.OperationType;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimResponse {


    @JsonProperty("contract_number")
    private final String contractNumber;
    private Long id;
    @JsonProperty("service_type")
    private OperationType serviceType;
    @JsonProperty("service_types")
    private List<OperationType> serviceTypes;
    @JsonProperty("number_car")
    private String trackNumber;
    @JsonProperty("number_trailer")
    private String trailerNumber;
    private String checkpoint;
    @JsonProperty("service_payer")
    private String payer;
    private String carrier;
    private String comment;
    @JsonProperty("document_photos")
    private Set<String> documents = new HashSet<>();
    private ClaimState state;
    private Long timestamp;
    @JsonProperty("manager_ids")
    private List<Long> managerIds;
    @JsonProperty("is_have_new_message")
    private boolean haveNewMessages;
    private DeclarantResponse declarant;




    public ClaimResponse(ClaimEntity other, List<Long> managerIds, DeclarantResponse declarant){
        this.id = other.getId();
        this.serviceType = other.getOperationType();
        this.trackNumber = other.getTrackNumber();
        this.trailerNumber = other.getTrailerNumber();
        this.checkpoint = other.getCheckPoint();
        this.payer = other.getPayer();
        this.carrier = other.getCarrier();
        this.comment = other.getComment();
        this.documents = other.getDocuments();
        this.declarant = declarant;
        this.state = other.getState();
        this.timestamp = other.getTimestamp()==null?new Date().getTime():other.getTimestamp();
        this.managerIds = managerIds;
        this.haveNewMessages = other.isHaveNewMessages();
        this.serviceTypes = new ArrayList<>();
        for (OperationType operationType: other.getOperationTypes())
            this.serviceTypes.add(operationType);
        this.contractNumber = other.getContractNumber();
    }

    public Long getId() {
        return id;
    }

    public OperationType getServiceType() {
        return serviceType;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public String getCheckpoint() {
        return checkpoint;
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

    public List<Long> getManagerIds() {
        return managerIds;
    }

    public boolean isHaveNewMessages() {
        return haveNewMessages;
    }

    public DeclarantResponse getDeclarant() {
        return declarant;
    }

    public List<OperationType> getServiceTypes() {
        return serviceTypes;
    }
}
