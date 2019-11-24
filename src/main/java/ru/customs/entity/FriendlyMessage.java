package ru.customs.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FriendlyMessage {

    private String id;
    private String text;
    private String name;
    private String dateTime;

    private String b;
    private String c;
    private String d;
    private String f;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name) {
        this.text = text;
        this.b = text;
        this.name = name;
        this.c = name;
        this.dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date(System.currentTimeMillis()));
        this.f = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }
}

