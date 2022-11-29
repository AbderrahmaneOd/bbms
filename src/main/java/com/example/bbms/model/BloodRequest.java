package com.example.bbms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BloodRequest {
    private final StringProperty id;
    private final StringProperty date;
    private final StringProperty priority;
    private final StringProperty bloodType;
    private final StringProperty status;
    private final StringProperty quantity;
    private final StringProperty hospitalID;

    public BloodRequest()
    {
        id = new SimpleStringProperty(this, "id");
        date = new SimpleStringProperty(this, "date");
        priority = new SimpleStringProperty(this, "priority");
        status = new SimpleStringProperty(this, "status");
        bloodType = new SimpleStringProperty(this, "bloodType");
        quantity = new SimpleStringProperty(this, "quantity");
        hospitalID = new SimpleStringProperty(this, "hospital id");

    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty dateProperty() { return date; }
    public String getDate() { return date.get(); }
    public void setDate(String newName) { date.set(newName); }

    public StringProperty priorityProperty() { return priority; }
    public String getPriority() { return priority.get(); }
    public void setPriority(String newName) { priority.set(newName); }


    public StringProperty statusProperty() { return status; }
    public String getStatus() { return status.get(); }
    public void setStatus(String newAddress) { status.set(newAddress); }

    public StringProperty quantityProperty() { return quantity; }
    public String getQuantity() { return quantity.get(); }
    public void setQuantity(String newAddress) { quantity.set(newAddress); }

    public StringProperty bloodTypeProperty() { return bloodType; }
    public String getBloodType() { return bloodType.get(); }
    public void setBloodType(String newName) { bloodType.set(newName); }

    public StringProperty hospitalIdProperty() { return hospitalID; }
    public String getHospitalId() { return hospitalID.get(); }
    public void setHospitalId(String newHospitalID) { hospitalID.set(newHospitalID); }

}
