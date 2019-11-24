package ru.customs.entity;


import javax.persistence.*;

@Entity
@Table(name = "fcm_token")
public class FCMTokenEntity {

    @Id
    @Column(name="user_token")
    private String userToken;
    @Column(name="fcm_token")
    private String FCMToken;

    public FCMTokenEntity() {
    }

    public FCMTokenEntity(String userToken, String FCMToken) {
        this.userToken = userToken;
        this.FCMToken = FCMToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getFCMToken() {
        return FCMToken;
    }

    public void setFCMToken(String FCMToken) {
        this.FCMToken = FCMToken;
    }
}
