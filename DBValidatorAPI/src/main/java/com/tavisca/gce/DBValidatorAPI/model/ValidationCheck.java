package com.tavisca.gce.DBValidatorAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ValidationCheck {

    @Id
    private String tid;
    private String input;
    private boolean isValid;
    private Date timestamp;
    private String serviceFrom;
    private String serviceTo;

    public ValidationCheck() {
        super();
    }

    public ValidationCheck(String transaction_id, String input, boolean isValid,
                           Date timestamp, String serviceFrom, String serviceTo) {
        super();
        this.tid = transaction_id;
        this.input = input;
        this.isValid = isValid;
        this.timestamp = timestamp;
        this.serviceFrom = serviceFrom;
        this.serviceTo = serviceTo;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
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
                "tid='" + tid + '\'' +
                ", input='" + input + '\'' +
                ", timestamp=" + timestamp +
                ", serviceFrom='" + serviceFrom + '\'' +
                ", serviceTo='" + serviceTo + '\'' +
                '}';
    }
}
