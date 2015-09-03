package com.waleedkhan.sample.model;

import java.util.Date;

public class Batch {
    private String batchId;
    private Status status;
    private Date lastUpdate;
    private String clientName;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Status getStatus() {
        return status;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getClientName() {
        return clientName;
    }
}
