package com.tavisca.gce.IncomingRequestAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class UserInput {

    @Id
    private String id;
    private String input;
    private Date timestamp;
    private String serviceFrom;
    private String serviceTo;

    public UserInput() {
        super();
    }

    public UserInput(String input, Date timestamp, String serviceFrom, String serviceTo) {
        super();
        this.id = UUID.randomUUID().toString();
        this.input = input;
        this.timestamp = timestamp;
        this.serviceFrom = serviceFrom;
        this.serviceTo = serviceTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getServiceFrom() {
        return serviceFrom;
    }

    public void setServiceFrom(String serviceFrom) {
        this.serviceFrom = serviceFrom;
    }

    public String getServiceTo() {
        return serviceTo;
    }

    public void setServiceTo(String serviceTo) {
        this.serviceTo = serviceTo;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "id='" + id + '\'' +
                ", input='" + input + '\'' +
                ", timestamp=" + timestamp +
                ", serviceFrom='" + serviceFrom + '\'' +
                ", serviceTo='" + serviceTo + '\'' +
                '}';
    }
}
