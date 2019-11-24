package ru.customs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Long managerId;
    private Long claimId;
    private MessageState state;
    private Long declarantId;

    public MessageEntity() {
    }

    public MessageEntity(String text, Long managerId, Long claimId, Long declarantId, MessageState state) {
        this.text = text;
        this.managerId = managerId;
        this.claimId = claimId;
        this.declarantId = declarantId;
        this.state = state;
    }

    public MessageEntity(Long id, String text, Long managerId, Long claimId, Long declarantId, MessageState state) {
        this.id = id;
        this.text = text;
        this.managerId = managerId;
        this.claimId = claimId;
        this.declarantId = declarantId;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getManagerId() {
        return managerId;
    }

    public Long getClaimId() {
        return claimId;
    }

    public MessageState getState() {
        return state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public void setState(MessageState state) {
        this.state = state;
    }

    public Long getDeclarantId() {
        return declarantId;
    }

    public void setDeclarantId(Long declarantId) {
        this.declarantId = declarantId;
    }
}
